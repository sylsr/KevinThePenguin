#! /bin/bash
#Author: Tyler Manning
BUILD_DIR=$(PWD)
cd ../
BASEDIR=$(PWD)
cd lib
LIB=$(PWD)

compile(){
echo Createing directory $BUILD_DIR/output
mkdir $BUILD_DIR/output
#Compile all java sources
cd $BASEDIR
echo Adding dependencies to the classpath


for i in $LIB/*.jar; do
    CLASSPATH=$CLASSPATH:$i
done
CLASSPATH=`echo $CLASSPATH | cut -c2-`

echo $CLASSPATH

echo Moving into $BASEDIR for compile
rm -rf $BUILD_DIR/sources.txt
cmd.exe /C "dir /s /B "*.java" > build\sources.txt"

echo Generated $BUILD_DIR/sources.txt
javac -verbose -classpath $CLASSPATH @$BUILD_DIR/sources.txt -d $BASEDIR/bin

echo Creating jar file in $BUILD_DIR/output
cd $BASEDIR
cd bin
jar cvmf META-INF/MANIFEST.MF ../build/output/app.jar org/kevin/*/*

}

clean(){
cd $BUILD_DIR
echo DELETING GENERATED DIRECTORY $BUILD_DIR/output/
rm -rf output/
echo DELETING GENERATED FILE $BUILD_DIR/sources.txt
rm -rf sources.txt
cd $BASEDIR
echo DELETING GENERATED DIRECTORY $BASEDIR/bin/org
rm -rf bin/org/
}

clean
compile