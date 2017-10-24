<?php

namespace App\Model\Table;

use Cake\ORM\Table;

class ExigenciasTable extends Table
{
	public function initialize(array $config)
	{
		$this->addBehavior('Timestamp');
	}
}
