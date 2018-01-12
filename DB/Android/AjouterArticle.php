<?php
header('Content-type: application/json');
	try
	{
		$db = new PDO('mysql:host=localhost;port=3306;dbname=projet_android;charset=utf8', 'root', '');
	}
	catch(Exception $e)
	{
		die('Erreur : '.$e->getMessage());
	}
	$nom = $_POST['nom'];
	$descriptif = $_POST['descriptif'];
	$prix = $_POST['prix'];
	$etat = $_POST['etat'];
	$localite = $_POST['localite'];
	$livraison = $_POST['livraison'];

	
	$sql = "SELECT * FROM Article WHERE nomArticle=? AND descriptifArticle = ? AND prixArticle = ?";
	$stmt = $db->prepare($sql);
	$stmt->execute(array($nom,$descriptif,$prix));
	if(($res=$stmt->fetch())==null){
		$sql = "SELECT idLocalite FROM Localite WHERE nomLocalite =?";
		$stmt = $db->prepare($sql);
		$stmt->execute(array($localite));
		if(($res=$stmt->fetch())!=null){
			$idLocalite = $res["idLocalite"];
			$sql = "INSERT INTO Article(nomArticle, descriptifArticle, prixArticle, etatArticle, idLocalite, livraisonArticle) VALUES(?,?,?,?,?,?)";
			$stmt = $db->prepare($sql);
			$stmt->execute(array($nom,$descriptif,$prix,$etat,$idLocalite,$livraison));
			echo "1";
		}
		else{
			echo "2";
		}
	}
	else{
		echo "3";
	}
?>