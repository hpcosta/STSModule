STSModule
===

TABLE OF CONTENTS
=========================

1. [Introduction](#1-introduction)
2. [Technical Information](#2-tech)
3. [Installation](#3-installation)
 	- 3.1. [External Libraries](#31-external-libraries)
    - 3.1.1 [NLP libraries](#32-nlp-libraries)
    - 3.1.2 [Semantic Similarity Libraries](#32-semantic-similarity-libraries)
4. [Requirements](#4-requirements)
5. [License](#5-license)



1. INTRODUCTION
=========================
STSModule (Semantic Textual Similarity Module) aims at helping users computing the semantic similarity between either sentences or documents in English. Similarity measures play an important role in a wide variety of NLP applications. By a way of example, Information Retrieval (IR) relies on semantic similarity in order to determine the best result for a related query. Semantic similarity also plays a crucial role in other applications such as Paraphrasing and Translation Memory (TM). However, computing semantic similarity between sentences and documents remains a complex and difficult task. As an attempt to fulfil this gap, STSModule aims at offering the user with a simple, yet very efficient approach to compute semantic similarity by combining semantic resources with statistical methods.



2. TECHNICAL INFORMATION
=========================



* This package provides several abstraction methods to BabelNet.
	* Semantic mesures for English?
		* The MySemanticSimilarityMeasures class wraps all the semantic similarity measures offered by the STSModule. Within the SemanticMeasuresManager class you will find a demo() that demonstrates how you can use them. Please have a closer look at the main method located at *'src/measures/SemanticMeasuresManager'*
			* SemanticMeasuresManager semanticSimilarity = new SemanticMeasuresManager(Constants.EN);
			* semanticSimilarity.calculatingSemanticSimilarityScores(sentence1, sentence2);

* Apart from that this program also includes several abstraction methods to perform various NLP tasks, such as: POS Tagging (TreeTagger); Lemmatisation (TreeTagger); Stemming (Snowball); Tokenisation (OpenNLP); Sentence Delimitation (OpenNLP); NER (OpenNLP); and Stopword Checker. Hereafter we describe how these methods can be called.
	* NLPManager nlpManager = new NLPManager(Constants.EN); // receives *the language*
	* The NLPManager class wraps all the NLP methods offered by the PreProcessor. Within this class you will find a demo() that demonstrates how you can use all these methods for various languages. Please have a closer look at the demo() method located at *'src/nlp/NLPManager'*


3. INSTALATION
=========================

1. Import the project to your Java editor.

2. Copy the folder 'config' and 'resources' to the root of your project (it should be at the same level as the src folder).
	* The folder 'internalResources' contains models for the:
		* TreeTagger (English, French, German, Italian, Portuguese and Spanish)
		* OpenNLP (tokeniser, sentence splitter and NER - only for English)
		* Stopword files (German, English, Italian, Portuguese and Spanish)

	* the folder 'config' contains configuration files for the Semantic Similarity Measures. You need to configure the folowing files and parameters, see step 3 first:
		* adw.properties 
			* wn30g.ppv.path= path to: /externalResources/adwResources/ppvs.30g.5k/
			* offset.map.file= path to: /externalResources/adwResources/offset2ID.map.tsv	
		* jlt.properties
			* wordnet.wordnetData3.0= path to: /externalResources/adwResources/WordNet-3.0/dict
			* stopwords.FilePrefix = path to: /externalResources/adwResources/jlt/stopwords/stopwords
			* stanford.pos.model= path to: /externalResources/adwResources/jlt/stanford/left3words-wsj-0-18.tagger
		* apart from that, the folder 'config' also contains a configuration file for the TreeTagger. You will need have the TreeTagger intalled in your computer an configure the treetagger.properties file.

3. Copy the folder 'externalResources' to your workspace (it should be at the same level as your project is).
	* this folder contains semantic signatures for the Semantic Similarity Measures

	* please check if the Semantic signatures are up-to-date through http://lcl.uniroma1.it/adw/ppvs.30g.5k.tar.bz2, if yes go to step 4.

		* if an update is required: Extract the downloaded file into a directory of your choice, I sugest you to use: 
			* /resources/adwResources/ (it should be at the same level as your project is)

		* then, update the 'wn30g.ppv.path' entry in the 'config/adw.properties' file, with the directory containing semantic signatures.






## 3.1 External Libraries 

This section is important to let you know what libraries are used in this project, as weel as to know how to update the resources or models.

#### 3.1.1 Semantic Similarity Libraries
	* ADW - Semantic Similarity Library
		* adw.v1.0, read: ADW-README.txt
		* This package provides an implementation of Align, Disambiguate, and Walk (ADW). ADW is a WordNet-based approach for measuring semantic similarity of arbitrary pairs of lexical items, from word senses to full texts. The approach leverages random walks on semantic networks for modeling lexical items.

#### 3.1.2 NLP libraries
	* TreeTagger
		* provides a POS Tagger for EN, SP, PT, FR, DE, IT and RU
		* The following java library allows to use TreeTagger in Java.
			* org.annolab.tt4j-1.0.15

	* Stemmer 
		* provides a Stemmer for EN, SP, PT, FR, DE, IT and RU
		* the following java library allows to use Stemmer in Java]
			* org.tartarus.snowball

	* OpenNLP
		* provides a **sentence splitter** and **tokenization** in EN, but can be used for at least EN, PT and SP
		* also provides **NER** for EN and SP, see models available through http://opennlp.sourceforge.net/models-1.5/
		* the following java library allows to use OpenNLP in Java
			* opennlp-maxent-3.0.3;
			* opennlp-tools-1.5.3; 
			* opennlp-uima-1.5.3
	
		* you can find these models inside the project folder, more specificaly in the "/resources/opennlpmodels/..." folder.
			* contains the following models for English:
				* Date name finder model.			
				* Location name finder model.		
				* Money name finder model.		
				* Organization name finder model.	
				* Percentage name finder model.	
				* Person name finder model.		
				* Time name finder model.
			* and the following models for Spanish:
				* Location name finder model, trained on conll02 shared task data.
				* Organization name finder model, trained on conll02 shared task data.	
				* Person name finder model, trained on conll02 shared task data.	
				* Misc name finder model, trained on conll02 shared task data.
			* the English and Spanish models are loaded by the 'NEREnModelsLoader' and 'NEREsModelsLoader' classes, respectivly.




#### 3.1.3 Dictionaires
	* BabelNet WebService
		* requires:
			* commons-io-2.4.jar
			* jsoup-1.8.1.jar




4. REQUIREMENTS
=========================

- Java 6 (JRE 1.6) or higher

- Semantic signatures (see [Installation](#3-instalation))
- WordNet 3.0 dictionary files (already included in the resources directory)
- Several models (already included either in the 'internalResources' or in the 'externalResources' folder)



5. LICENSE
=========================

Copyright (c) 2013-2016 
Hernani Costa @LEXYTRAD, University of Malaga, Spain. 
All rights reserved.

For more information please contact:

> hercos (at) uma (dot) es



### Follow me on
<!-- Please don't remove this: Grab your social icons from https://github.com/carlsednaoui/gitsocial -->

<!-- display the social media buttons in your README -->

[![alt text][1.1]][1]
[![alt text][2.1]][2]
[![alt text][3.1]][3]
[![alt text][4.1]][4]



<!-- links to social media icons -->
<!-- no need to change these -->

<!-- icons with padding -->

[1.1]: http://i.imgur.com/tXSoThF.png (twitter icon with padding)
[2.1]: http://i.imgur.com/P3YfQoD.png (facebook icon with padding)
[3.1]: http://i.imgur.com/yCsTjba.png (google plus icon with padding)
[4.1]: http://i.imgur.com/0o48UoR.png (github icon with padding)


<!-- links to your social media accounts -->
<!-- update these accordingly -->

[1]: https://twitter.com/#!/hernanimax
[2]: https://www.facebook.com/hernani.costa.161
[3]: https://plus.google.com/+HernaniCosta
[4]: https://github.com/hpcosta
