<?php

namespace App\Controller;
use Cake\Event\Event;

class UsuariosController extends AppController
{
	public function beforeFilter(Event $event)
    {
        parent::beforeFilter($event);
        // Allow users to register and logout.
        // You should not add the "login" action to allow list. Doing so would
        // cause problems with normal functioning of AuthComponent.
        $this->Auth->allow(['logout']);
    }
	public function index(){
		$this->viewBuilder()->layout('ppal');
		$this->set('usuarios', $this->Usuarios->find('all'));
	}

	public function login(){
		$this->viewBuilder()->layout('login');
		if ($this->request->is('post')) {
            $user = $this->Auth->identify();
            if ($user) {
                $this->Auth->setUser($user);
                return $this->redirect($this->Auth->redirectUrl());
            }
            $this->Flash->error(__('Datos incorrectos'));
        }
	}

    public function logout(){
        return $this->redirect($this->Auth->logout());
    }

    public function add()
    {
	$this->viewBuilder()->layout('cysoD');
        $usuarios = $this->Usuarios->newEntity();
        if ($this->request->is('post')) {
            $usuarios = $this->Usuarios->patchEntity($usuarios, $this->request->data);
            if ($this->Usuarios->save($usuarios)) {
                $this->Flash->success(__('Guardado.'));
                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('No guardado.'));
        }
        $this->set('usuarios', $usuarios);
    }
}
