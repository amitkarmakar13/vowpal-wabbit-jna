#include <stdio.h>
#include "../vowpalwabbit/parser.h"
#include "../vowpalwabbit/vw.h"

using namespace std;

vw* vw;

extern "C" void initialize(const char* command) {
  vw = VW::initialize(command);
}

extern "C" double getPrediction(char* example_string) {
  example *vec2 = VW::read_example(*vw, example_string);
  vw->learn(vec2);
  VW::finish_example(*vw, vec2);
  return (((label_data*)vec2->ld)->prediction);
}

extern "C" void closeInstance() {
  VW::finish(*vw);
}
