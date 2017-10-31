JFLAGS = -g
JC = javac
JVM= java  # Added by Agustín González
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = ServidorCentral.java ServidorDistrito.java Cliente.java Titan.java DistritoDatos.java
#ServidorDistritos.java Cliente.java Titan.java

# main variable: Added by Agustín González
MAIN = ServidorCentral ServidorDistrito Cliente

default: classes

classes: $(CLASSES:.java=.class)

# run tarjet added by Agustín González
run: 
	$(JVM) $(MAIN)

clean:
	$(RM) *.class