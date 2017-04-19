import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

Classifier modelo = (Classifier)SerializationHelper.read("models/model.model")
DataSource fuenteEntrada = new DataSource("./entrada.arff")
Instances dataEntrada = fuenteEntrada.dataSet

if (dataEntrada.classIndex() == -1) {
	dataEntrada.setClassIndex(dataEntrada.numAttributes() - 1);
}
for(int i = 0; i < dataEntrada.numInstances(); i++) {
	def modelRes =modelo.classifyInstance(dataEntrada.instance(i))
	if(modelRes as int == 1){
		println "cartoon"
	}
	else{
		println "real"
	}
}             