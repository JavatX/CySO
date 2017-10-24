<?php

namespace App\Model\Table;

use Cake\ORM\Table;

class FotosTable extends Table
{
	public function initialize(array $config)
	{
		$this->addBehavior('Timestamp');
	}
}
