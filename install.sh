cd jPDDL
call mvn install
cd ..
cd jPDDL-Tools
call mvn install
cd ..
cd AUV-Domain
call mvn install
cd ..
cd AUV-Problem
call mvn install -Dmaven.test.skip=true
cd ..
cd Perestroika-Domain
call mvn install
cd ..
cd Perestroika-Problem
call mvn install -Dmaven.test.skip=true
cd ..
cd jPDDL-Console
call mvn install
cd ..