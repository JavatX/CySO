<?php
// src/Model/Entity/User.php

namespace App\Model\Entity;

use Cake\Auth\DefaultPasswordHasher;
use Cake\ORM\Entity;

class Usuario extends Entity
{

    // Make all fields mass assignable except for primary key field "id".
   /* protected $_accessible = [
        '*' => true,
        'NIF' => false
    ];*/

 
    protected function _setContraseña($password)
    {
//	die("hasheando");
        return (new DefaultPasswordHasher)->hash($password);
    }


}

