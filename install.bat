cd jPDDL
call mvn clean install
cd ..
cd jPDDL-Tools
call mvn clean install
cd ..
cd AUV-Domain
call mvn clean install
cd ..
cd AUV-Problem
call mvn clean install -Dmaven.test.skip=true
cd ..
cd Perestroika-Domain
call mvn clean install
cd ..
cd Perestroika-Problem
call mvn clean install -Dmaven.test.skip=true
cd ..
cd jPDDL-Console
call mvn clean install
cd ..