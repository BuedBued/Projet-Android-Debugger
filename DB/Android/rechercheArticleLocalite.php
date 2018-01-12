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

	$localite = $_POST['localite'];

	$tab = array();
	$sql = "SELECT * FROM Article a INNER JOIN Localite l ON a.idLocalite = l.idLocalite WHERE l.nomLocalite = '".$localite."'";
	foreach($db->query($sql) as $res){
		$tmp = [
			'nom'=>$res['nomArticle'],
			'descriptif'=>$res['descriptifArticle'],
			'prix'=>$res['prixArticle'],
			'localite'=>$res['nomLocalite'],
			'etat'=>$res['etatArticle'],
			'livraison'=>$res['livraisonArticle']
		];
		array_push($tab,$tmp);
	}
	echo json_encode($tab);
?>