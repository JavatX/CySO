<?php

namespace App\Model\Table;

use Cake\ORM\Table;

class ConstrutoresTable extends Table
{
	public function initialize(array $config)
	{
		$this->addBehavior('Timestamp');
	}
}
