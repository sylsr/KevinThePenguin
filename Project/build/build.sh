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

echo CLASSPATH: $CLASSPATH

echo Moving into $BASEDIR for compile
rm -rf $BUILD_DIR/sources.txt
cmd.exe /C "dir /s /B "*.java" > build\sources.txt"

echo Generated $BUILD_DIR/sources.txt
javac -verbose -classpath $CLASSPATH @$BUILD_DIR/sources.txt -d $BASEDIR/bin

echo Creating new MANIFEST.MF
cd $BASEDIR/lib
for i in *
do
dependencies="$dependencies jars/$i"
done	
mkdir $BUILD_DIR/META-INF
cd $BUILD_DIR/META-INF
rm -rf MANIFEST.MF
echo Manifest-Version: 1.0 >> MANIFEST.MF
echo Class-Path: $dependencies >> MANIFEST.MF
echo Main-Class: org.kevin.main.LaunchKevin >>MANIFEST.MF

echo Copying libs folder
cp -r $BASEDIR/lib/ $BUILD_DIR/output/jars/

echo Copying the img folder into the bundle.
cp -r $BASEDIR/img $BUILD_DIR/output/img/

echo Copying the config folder into the bundle.
cp -r $BASEDIR/config $BUILD_DIR/output/config

echo Creating jar file in $BUILD_DIR/output
cd $BASEDIR
cd bin
jar cvmf ../build/META-INF/MANIFEST.MF ../build/output/app.jar org/kevin/*/*

create_db
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

create_db(){
echo Creating the database
cd $BUILD_DIR
cd output
$BASEDIR/db/installers/winTools/sqlite-tools-win32-x86-3120200/sqlite3.exe kevin.db "CREATE TABLE test (ID INT PRIMARY KEY NOT NULL)"
}

if [ -z $1 ]
then
	echo Please see https://github.com/sylsr/KevinThePenguin/wiki/Build for build instructions.
	exit 1
fi

if [ $1 == 'clean' ]
then
	echo Cleaning the working build.
	clean
fi

if [ $1 == 'run' ]
then
	echo Running the jar without build.
	cmd.exe /C "java -jar app.jar -zxcvbnmasdfghjklqwertyuiop"
fi

if [ $1 == 'debug' ]
then
	clean
	compile
	cd $BUILD_DIR/output
	cmd.exe /C "java -jar app.jar -zxcvbnmasdfghjklqwertyuiop"
fi

if [ $1 == 'release' ]
then
	clean
	compile
	cd $BUILD_DIR/output
	java -jar app.jar
fi
