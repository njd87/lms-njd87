# Struct Optimization and CodeGen

Struct optimizations were made for getter and setter functions in the same way we optimized arrays. For any struct, s, any get statement that precedes another get statement of the same attribute will get overwritten.

```scala
// setter(struct, val1); ...; setter(struct, val1) => setter(struct, val1); ()
case ("lib-function-setter", List(func: Exp, field: Exp, b, as: Exp, v1)) if ({curEffects.get(as).flatMap({ case (lw, _) => findDefinition(lw)}) match {
        case Some(Node(_, "lib-function-setter", List(f, _, _, _, v2), _)) if (f == func) & (v1 == v2) => true  
        case _ => false
    }
    }) => Some(Const(()))
```

Similarly, setter statements that set on get statements result in a blank block.

```scala
// setter(struct, val1) = getter_val1(struct) => ()   side condition: no write in-between!
    case ("lib-function-setter", List(func_name: Exp, field: Exp, b, as: Exp, v1: Exp)) => {
      curEffects.get(as) match {
        case Some((_, lst)) => if (lst.contains(v1)) {
                                Some(Const(()))
                              } else {
                                None
                              }
        case None => None
      }
    }
```

See also the "state.h" header file in LMS-Clean that adds a struct that LMS-Koika can work with.

Code generation for setters and getters were also added:

```scala
case Node(s, "lib-function-getter", Const(m:String) :: Const(_tmp:String) :: Const(pkeys: Set[Int]) :: rhs, _) =>
      val last = rhs.length - 1
      emit(s"$m(");
      rhs.zipWithIndex.foreach { case(r, index) =>
        if (pkeys.contains(index)) emit("&")
        shallow(r)
        if (index < last) emit(", ")
      }
      emit(")")
    case Node(s, "lib-function-setter", Const(m:String) :: Const(_tmp:String) :: Const(pkeys: Set[Int]) :: rhs, _) =>
      val last = rhs.length - 1
      emit(s"$m(");
      rhs.zipWithIndex.foreach { case(r, index) =>
        if (pkeys.contains(index)) emit("&")
        shallow(r)
        if (index < last) emit(", ")
      }
      emit(")")
    case a => println(a); super.shallow(n)
```

# Nested-If Optimization
Often times when working with the updated registration functions in interpc, we would get nested-if statements with arrays that resulted in something like the following (from interpc0_5)

```c

    int* Snippet(int* x0) {
      x0[0] = 1;
      int x1 = x0[1] + x0[1];
      x0[1] = x1;
      x0[0] = 2;
      if (x1 == 0) x0[0] = 0;
      int x2 = x0[0];
      if (x2 == 0) {
        x0[0] = 1;
        x0[1] = x0[1] + x0[1];
      }
      if (x2 == 1) {
        x0[0] = 2;
        if (x0[1] == 0) x0[0] = 0;
      }
      int x3 = x0[0];
      if (x3 == 0) {
        x0[0] = 1;
        x0[1] = x0[1] + x0[1];
      }
      if (x3 == 1) {
        x0[0] = 2;
        if (x0[1] == 0) x0[0] = 0;
      }
      return x0;
    }
```

So, in the backend, we add a recursive statment to our array-get check to resemble the following:

```scala
case ("array_get", List(as:Exp,i:Exp)) => {
          def rec(x: Sym): Option[Exp] = {
            findDefinition(x).flatMap({
              case Node(_, "array_set", List(_, i2: Exp, value: Exp), es) =>
                (i,i2) match {
                  case _ if i == i2 => Some(value)
                  case (Const(c),Const(c2)) if c != c2 && es.hdeps.size==1 =>
                    rec(es.hdeps.toSeq.head)
                  case _ => None
                }
              
              // Trying a recursive call to rec
              case Node(_, _, _, es) =>
                rec(es.hdeps.toSeq.head)
              
              case _ => None
            })
          }
          curEffects.get(as).flatMap({ case (x, _) => rec(x) })
        }
```

So, instead of checking for values in the array like our nested if-statement example shows, the code preemptivley goes through and validates the values at each index (in other words, we don't have to check for values in the array if we know they are not important for the return value). So, interpc0_5 now returns the following:

```c
int* Snippet(int* x0) {
      x0[0] = 1;
      int x1 = x0[1] + x0[1];
      x0[1] = x1;
      x0[0] = 2;
      if (x1 == 0) x0[0] = 0;
      return x0;
    }
```

# Next Steps
Unfortunatley, there is still many unanswer questions on how to proceed. One problem we attempted to address in this research project is a question on how to optimize if-statments on Rep[Boolean] values. For example, let's say we have the following:

```scala
if (arg) {
  aBlock;
} else {
  bBlock;
}
cBlock;
dBlock;
```

where arg represents a Rep[Boolean] value. Our code, as of right now, has no way of telling whether or not aBlock or bBlock needs to be run, so it keeps the if-statement as it is. Going forward, we suggest using CPS to rewrite the code above as:

```scala
if (arg) {
  aBlock;
  cBlock;
  dBlock; 
} else {
  bBlock;
  cBlock;
  dBlock;
}
```

then finding the output value for each segment of the if-statement, writing only what needs to be written.
