<?php

namespace App\Controller;
use Cake\Event\Event;
use Cake\Auth\DefaultPasswordHasher;

class UsuariosController extends AppController
{
    public $paginate = [
        'limit' => 20,
        'order' => [
            'Usuarios.NIF' => 'asc'
            ]
    ];

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

    public function inspectors(){
        $this->viewBuilder()->layout('cysoU');
        $query = $this->Usuarios->find('all')->where(['Usuarios.tipo LIKE' => 'inspector']);
        $this->set('usuarios', $this->paginate($query));
    }

    public function mod($id = null)
    {
        $this->viewBuilder()->layout('cysoU');
        $usuario = $this->Usuarios->get($id);
        if ($this->request->is('post')) {
            $data = $this->request->data;
            $usuario->nombre = $data['nombre'];
            $usuario->apellidos = $data['apellidos'];
            $usuario->NIF = $data['NIF'];
            if($data['contrasena'] === $data['contrasena2']){
                if($data['contrasena'] != ""){
                    $usuario->contraseña = $data['contrasena'];
                }
            }else{
                $this->Flash->error(__('Las contraseñas no coinciden.'));
                return $this->redirect(['action' => 'mod', $id]);
            }
            if ($this->Usuarios->save($usuario)) {
                $this->Flash->success(__('Datos modificados.'));
                return $this->redirect(['action' => 'mod', $id]);
            }
            $this->Flash->error(__('No guardado.'));
        }
        $this->set(compact('usuario'));
    }

    public function add()
    {
	$this->viewBuilder()->layout('cysoU');
        $usuarios = $this->Usuarios->newEntity();
        if ($this->request->is('post')) {
            $usuarios = $this->Usuarios->patchEntity($usuarios, $this->request->data);
            if ($this->Usuarios->save($usuarios)) {
                $this->Flash->success(__('Usuario añadido.'));
                return $this->redirect(['action' => 'add']);
            }
            $this->Flash->error(__('No guardado.'));
        }
        $this->set('usuarios', $usuarios);
    }
}
