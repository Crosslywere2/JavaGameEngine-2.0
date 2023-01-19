#!/bin/bash
javac -d build @files-to-build;
cd build;
jar -cf JavaGameEngine-2.0.jar *;
exit 0
