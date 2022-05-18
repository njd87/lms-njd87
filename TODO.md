# Learn

- [ ] Understand the LMS Clean code for arrays
  - [ ] See `lms-clean/src/main/scala/lms/collection/ArrayOps.scala`
- [ ] Understand the LMS Clean support for C structs and library functions
  - [ ] See `lms-clean/src/main/scala/lms/thirdparty/c_lib_utils.scala`
- [ ] Understand the `GraphBuilderOpt` rewrite optimizations
- [ ] Understand the effect system

# Tasks

- [ ] Devise a way to use C structs that preserve the read-write optimizations similar to arrays and vars
  - [ ] Design a new struct mechanism with explicit read/write field accesses
- [ ] Optimize if structure further
  - [ ] Experiment with this change: https://github.com/namin/lms-clean/commit/ade5a1b044ca35da64179499fb8302e2a26476d2
    - [ ] See what changes in the generated code, and why it isn't enough
- [ ] Make sure the generated code can compile and run
  - [ ] Have a small example with structs and library functions that compiles and runs

# Notes

- [x] file LMS bug regarding array nested in collection:
      https://github.com/TiarkRompf/lms-clean/issues/122
- [x] extend LMS to that it considers chains of writes when reading
      so that branches on PC can be optimized:
      https://github.com/namin/lms-clean/commit/ade5a1b044ca35da64179499fb8302e2a26476d2
- [ ] this extension wasn't enough, we really want `if` hoisting
- [ ] optimize per program path
- [ ] read LMS paper on Staged Abstract Interpreters:
      https://www.cs.purdue.edu/homes/rompf/papers/wei-oopsla19.pdf
- [ ] find analyses for non-interference:
      for a function f(x,y,z,t), find which arguments do not matter
      (if we had a perfectly optimizing LMS, then arguments that do not matter would not appear in generated code)
      in general, we care about non-interference over traces
- [ ] eventually, apply to in-order and out-of-order processors
      (but in-order is hard enough!)
- [x] approach today is to detect constant-time at source code
  - no branch on secret
  - no indirect access dependong on secret
  - no div/non-constant time operations on the secret
  - not sufficient depending on processor!
  - not even necessary...
  - we will partially evaluate over the processor to verify true properties at the end
