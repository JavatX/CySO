<?php

namespace App\Controller;

use Cake\Auth\DefaultPasswordHasher;
class ConstructoresController extends AppController
{
	public $paginate = [
		'limit' => 9,
		'order' => [
			'Constructores.idConstructor' => 'asc'
			]
	];
	public function index()
	{
		$this->viewBuilder()->layout('cysoC');
		//$this->set('constructores', $this->Constructores->find('all'));
		$this->set('constructores', $this->paginate());
	}

	public function informacion($id = null)
	{
		$this->viewBuilder()->layout('cysoC');
		$constructor = $this->Constructores->get($id);
		$this->set(compact('constructor'));
	}

	public function isAuthorized($user)
	{
	    // All registered users can add articles
	    if ($this->request->action === 'index' || $this->request->action === 'informacion') {
	        return true;
	    }

	    /*// The owner of an article can edit and delete it
	    if (in_array($this->request->action, ['edit', 'delete'])) {
	        $articleId = (int)$this->request->params['pass'][0];
	        if ($this->Articles->isOwnedBy($articleId, $user['id'])) {
	            return true;
	        }
	    }*/

	    return parent::isAuthorized($user);
	}
}
