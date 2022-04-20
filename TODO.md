# TODOs

- [x] file LMS bug regarding array nested in collection:
      https://github.com/TiarkRompf/lms-clean/issues/122
- [ ] extend LMS to that it considers chains of writes when reading
      so that branches on PC can be optimized
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
