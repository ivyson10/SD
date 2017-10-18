<?php

$x = $HTTP_GET_VARS["num1"];
$y = $HTTP_GET_VARS["num2"];
$op = $HTTP_GET_VARS["operacao"];



if($op=="+")
    $z = $x + $y;

elseif($op=="-")
    $z = $x - $y;


elseif($op=="*")
    $z = $x * $y;


elseif ($op=="/")
    $z = $x/$y;

echo "O resultado e: $z";
?>