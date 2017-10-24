<?php

namespace App\Controller;

class LicenciasController extends AppController
{

	public $paginate = [
		'limit' => 20,
		'order' => [
			'Licencias.numLicencia' => 'asc'
			]
	];

	public function index()
	{
		$this->viewBuilder()->layout('cysoC');
		$this->set('exigencias', $this->paginate());
	}

	// public function add(){
	// 	$this->viewBuilder()->layout('cysoC');
	// 	$this->loadModel('Usuarios');
	// 	$arrayNIF = array();
	// 	$aux = $this->Usuarios->find('all')->where(['tipo LIKE' => 'cons'])->toArray();
	// 	for ($i=0; $i < count($aux); $i++) { 
	// 		$arrayNIF[$aux[$i]['NIF']] = $aux[$i]['NIF'];
	// 	}
	// 	$this->set(compact('arrayNIF'));
	// 	$this->set('usuarios', $this->Usuarios->find()->select(['NIF'])->where(['tipo LIKE' => 'cons']));
	// 	$construccion = $this->Construcciones->newEntity();
	// 	if ($this->request->is('post')) {
 //            $construccion = $this->Construcciones->patchEntity($construccion, $this->request->data);
 //            if ($this->Construcciones->save($construccion)) {
 //                $this->Flash->success(__('Guardado.'));
 //                return $this->redirect(['action' => 'index']);
 //            }
 //            $this->Flash->error(__('No guardado.'));
	//     }
 //        $this->set('construccion', $construccion);
	// }
	// public function listaConstrucciones()
	// {
	// 	$this->viewBuilder()->layout('cysoC');
	// 	$this->set('construcciones', $this->paginate());
	// }

	public function informacion($id = null, $cons = null)
	{
		$this->viewBuilder()->layout('cysoC');
		$licencia = $this->Licencias->get($id);

		$this->loadModel('Obras');
		$query = $this->Obras->find('all')->where(['Licencia_numLicencia LIKE' => $id]);
		$cumple = $query->first();
		print_r("$cumple");
		if(isset($cumple))
			$this->set('obras', $cumple);
		else{
			print("hola");
			$this->loadModel('Ocupaciones');
			$query = $this->Ocupaciones->find('all')->where(['Licencia_numLicencia LIKE' => $id]);
			$cumple = $query->first();
			$this->set('ocupacion', $cumple);
		}
	
		
		
		
		
		// $this->set('username', $nombre);
		$this->set(compact('licencia'));
		print_r($licencia);
	}

	public function buscar()
	{
		$this->viewBuilder()->layout('cysoC');
		if ($this->request->is('post')) {
            $article = $this->Construcciones->patchEntity($construcciones, $this->request->data);
            if ($this->Construcciones->save($construcciones)) {
                $this->Flash->success(__('Guardado.'));
                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('No guardado.'));
        }

		$this->set('construcciones', $construcciones);
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
