# prerequisitos

1. instalar python
2. instalar image theif 

    pip install colorthief

3. instalar scikit

    pip install scikit-image

# usos
```
beginProc
```
creal el modelo y los exporta como output.arff

```

beginProc <RUTA ABSOLUTA>
```
crea un arff en base al archivo y lo exporta como \<RUTA ABSOLUTA\>.arff

# ignorar atributos para weka
```
You could use the meta-classifier "FilteredClassifier" in combination 
with the "Remove" filter (in package 
weka.filters.unsupervised.attribute) in order to remove the unwanted 
attribute for the base classifier you're using.
```