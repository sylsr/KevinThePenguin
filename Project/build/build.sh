+#! /bin/bash
+#Author: Tyler Manning
+BASEPACKDIR="../src/org/kevin/"
+
+timestamp() {
+  date +"%T"
+}
+
+compile() {
+	#Go through all dirs and build java files into class files, then moves them to the bin dir
+	for d in $(PWD)/*
+	do
+	  echo "Compile in $d"
+	  for f in $d/*
+	  do
+	  	if [[ $f == *.java ]];then
+			echo "	javac $f"
+			javac $f -d ../../../bin
+	    fi
+	  done
+	done
+}
+
+clean() {
+	echo "Cleaning repo"
+	cd ../bin
+	rm -rf *
+	echo "rm -rf *"
+	cd ../build/output
+	rm -rf *.jar
+	cd ../
+}
+
+createjar() {
+#create the jar file
+echo "Creating executable jar"
+jar cfe ../build/output/app.jar org.kevin.main.LaunchKevin org/kevin/main/LaunchKevin.class
+echo "jar created in /build/output/"
+}
+
+clean
+echo "Build Begin: $(timestamp)"
+echo "cd $BASEPACKDIR"
+cd $BASEPACKDIR
+
+compile
+
+#move to bin folder
+cd ../../../bin
+
+createjar
+
+echo "Build finish: $(timestamp)"