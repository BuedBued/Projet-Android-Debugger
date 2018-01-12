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
	$tab = array();
	$sql = "SELECT * FROM localite";
	foreach($db->query($sql) as $ligne){
		$tmp = [
			'id'=>$ligne['idLocalite'],
			'nom'=>$ligne['nomLocalite']
		];
		array_push($tab,$tmp);
	}
	echo json_encode($tab);
?>