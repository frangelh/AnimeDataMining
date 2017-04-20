import std.algorithm;
import std.conv;
import std.file;
import std.getopt;
import std.path;
import std.process;
import std.stdio;
import std.string;

int main(string[] args) {
    string passedFileName = "";
    if (args.length < 3) {
        return -2;
    }
    try {
        getopt(args, "f", &passedFileName);
    }
    catch (Exception e) {

    }
    string outputFileName;
    if (passedFileName.empty) {
        outputFileName = "output.arff";
    }
    else {
        outputFileName = "entrada.arff";
    }
    if (outputFileName.exists) {
        outputFileName.remove();
    }

    File outputFile = File(outputFileName, "w");
    outputFile.initArff();

    if (passedFileName.empty) {
        File animeFile = File(args[1], "r");
        File realFile = File(args[2], "r");

        scope (exit)
            outputFile.close();

        string[] animeImages;
        while (!animeFile.eof()) {
            string line = animeFile.readln();
            if (line.empty)
                continue;
            animeImages ~= line.strip;
        }
        animeFile.close();
        string[] realImages;
        while (!realFile.eof()) {
            string line = realFile.readln();
            if (line.empty)
                continue;
            realImages ~= line.strip;
        }
        realFile.close();
        foreach (anim; animeImages) {
            outputFile.writeAllToFile(anim, "cartoon");
        }
        foreach (rea; realImages) {
            outputFile.writeAllToFile(rea, "real");
        }
    }
    else {
        if (passedFileName.exists) {
            outputFile.writeAllToFile(passedFileName, "real");
        }
        else {
            writeln("file does not exist");
            return -1;
        }
    }
    return 0;
}

void writeAllToFile(ref File outputFile, string filename, string fileType) {
    // outputFile.writeName(filename);
    outputFile.writeRealFaceCount(filename);
    outputFile.writeAnimeFaceCount(filename);
    outputFile.writeImageColores(filename);
    outputFile.writeImageFeatures(filename);
    outputFile.writeImageType(fileType);
}

void initArff(ref File outputFile) {
    outputFile.writeln("@relation EXAMPLE");
    // outputFile.writeln("@attribute Name string");
    outputFile.writeln("@attribute drawnFaceCount numeric");
    outputFile.writeln("@attribute realFaceCount numeric");
    outputFile.writeln("@attribute darkColorCount numeric");
    outputFile.writeln("@attribute brightColorCount numeric");
    outputFile.writeln("@attribute contrast numeric");
    outputFile.writeln("@attribute homogeneity numeric");
    outputFile.writeln("@attribute  dissimilarity numeric");
    outputFile.writeln("@attribute energy numeric");
    outputFile.writeln("@attribute imgType {real,cartoon}");
    outputFile.writeln("@data");
}

void writeName(ref File output, const string filename) {
    output.write("\"" ~ filename.strip.baseName ~ "\"");
}

void writeRealFaceCount(ref File output, const string filename) {
    string faceCountReal = "./faceCount %s ./realFace.xml".runCommand(filename);
    // output.write("," ~ faceCountReal);
    output.write(faceCountReal);
}

void writeAnimeFaceCount(ref File output, const string filename) {
    string animeCountReal = "./faceCount %s animeface.xml".runCommand(filename);
    output.write("," ~ animeCountReal);
}

void writeImageColores(ref File output, const string filename) {
    int darkc = 0, lightc = 0;
    string imageColores = "./imageDominantColors %s".runCommand(filename);
    string[] rowColors = imageColores.split("\n");
    foreach (color; rowColors) {
        if (color.canFind("dark")) {
            darkc++;
        }
        else {
            lightc++;
        }
    }
    output.write("," ~ to!string(darkc) ~ "," ~ to!string(lightc));
}

void writeImageFeatures(ref File output, const string filename) {
    int darkc = 0, lightc = 0;
    string imageFeatures = "./imgProc %s".runCommand(filename);
    output.write("," ~ imageFeatures);
}

void writeImageType(ref File output, string img) {
    output.writeln("," ~ img);
}

string runCommand(string cmd, string param) {
    string runnigComand = cmd.format("\"" ~ param ~ "\"");
    // writeln(runnigComand);
    return runnigComand.executeShell.output.strip;
}
