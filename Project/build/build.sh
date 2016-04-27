#! /bin/bash
#Author: Tyler Manning
BASEPACKDIR="../src/org/kevin/"

timestamp() {
  date +"%T"
}

compile() {
	#Go through all dirs and build java files into class files, then moves them to the bin dir
	echo "Core COMPILE:"
	#Just prints stuff out
	for dir in $(PWD)/*
	do
	  echo "	In: $dir"
	  for fl in $dir/*
	  do
	  	if [[ $fl == *.java ]];then
	  		filename="${fl##*/}"
			echo "		$filename"
	    fi
	  done
	done
	
	#Does the actual compile
	cd ../../
	echo $(PWD)
	dir=$(PWD)/*/*.java
	javac -cp -nowarn $dir org.kevin.main.LaunchKevin.java
}

clean() {
	echo "Cleaning repo"
	cd ../bin
	rm -rf *
	echo "rm -rf *"
	cd ../build
	rm -rf output
	
}

createjar() {
#create the jar file
echo "Creating executable jar"
mkdir ../build/output
jar cfe ../build/output/app.jar org.kevin.main.LaunchKevin org/kevin/main/LaunchKevin.class || { echo "Build Failed: $(timestamp)"; exit 1; }
echo "jar created in /build/output/"
}

clean
echo "Build Begin: $(timestamp)"
echo "cd $BASEPACKDIR"
cd $BASEPACKDIR

compile

#move to bin folder
cd ../../../bin

createjar

echo "Build finish: $(timestamp)"

if [[ $1 == debug ]];then
	cd ../build/output
	start "" "$SHELL" -c "java -jar app.jar"
fi
