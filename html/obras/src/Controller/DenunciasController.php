<?php

namespace App\Controller;

class DenunciasController extends AppController
{

	public $paginate = [
			'limit' => 20,
			'order' => ['Denuncias.construcciones_idConstruccion' => 'asc']
		];

	public function index($id = null)
	{
		$this->viewBuilder()->layout('cysoD');
		$this->paginate = [
			'limit' => 9,
			'order' => ['Denuncias.construcciones_idConstruccion' => 'asc']
		];
		if($id == null){
			$this->set('denuncias', $this->paginate());
		}else{
			$query = $this->Denuncias->find('all')->where(['Denuncias.construcciones_idConstruccion' => $id]);
			$this->set('denuncias', $this->paginate($query));
		}
	}

	public function recibidas($id = null)
	{
		$this->viewBuilder()->layout('cysoD');
		$query = "";
		$this->paginate = [
			'limit' => 9,
			'order' => ['Denuncias.construcciones_idConstruccion' => 'asc']
		];
		$this->loadModel('Construcciones');
		$qryC = $this->Construcciones->find()->select('idConstruccion')->where(['Constructor_Usuario_NIF LIKE' => $id]);
		$ar = $qryC->toArray();
		// print_r($ar[0]);
		$options = "[";
		$options2 = "SELECT * FROM denuncias WHERE ";
		for ($i=0; $i < count($ar); $i++) { 
			$options2 = $options2 . "construcciones_idConstruccion LIKE " . "'". $ar[$i]['idConstruccion'] . "'";
			$options = $options . "'Denuncias.construcciones_idConstruccion LIKE' => " . $ar[$i]['idConstruccion'];
			if($i == (count($ar)-1)):
				$options = $options . "]";
			else:
				$options2 = $options2 . " AND ";
				$options = $options . ", ";
			endif;
		}
		//printf($options2);
		// $query = $this->Denuncias->find('all')->where($options);
		// $query = $this->Denuncias->find('all')->where(['Denuncias.construcciones_idConstruccion' => $array]);
		$this->set('denuncias', $this->paginate($options2));
	}

	public function informacion($id = null)
	{
		$this->viewBuilder()->layout('cysoD');
		$denuncia = $this->Denuncias->get($id);
		// $this->loadModel('Usuarios');
		// $NIF = $construccion['Constructor_Usuario_NIF'];
		// $nombre = $this->Usuarios->get($NIF);
		// $this->set('username', $nombre);
		$this->set(compact('denuncia'));
		//print_r($denuncia);
		// print_r($nombre);
	}

	public function asignation($id = null)
	{
		$this->viewBuilder()->layout('cysoD');
		$query = $this->Denuncias->find('all')->where(['Inspector_Usuario_NIF' => $id]);
		$this->set('denuncias', $this->paginate($query));		
	}
/*
	public function listaConstrucciones()
	{
		$this->loadModel('Construcciones');
		$this->viewBuilder()->layout('cysoD');
		$this->set('construcciones', $this->Construcciones->find('all'));
	}
*/
	public function add($id = null)
	{
		$this->viewBuilder()->layout('cysoD');
		$this->set('cons', $id);
		$denuncia = $this->Denuncias->newEntity();
        if ($this->request->is('post')) {
        	// print_r($this->request->data);
        	$old = $this->request->data['foto']['tmp_name'];
        	// print_r($old);
        	$data = $this->request->data;
        	$data['foto'] = DS . 'obras' . DS . 'webroot' . DS . 'img' . DS . 'Denuncias' . DS . $data['numDenuncia'] . ".jpg";
        	// print_r($data);
            $denuncia = $this->Denuncias->patchEntity($denuncia, $data);
            // print_r($denuncia['foto']);
            move_uploaded_file($old, WWW_ROOT . 'img' . DS . 'Denuncias' . DS . $denuncia['numDenuncia'] . ".jpg");
            if ($this->Denuncias->save($denuncia)) {
                $this->Flash->success(__('Guardado.'));
                return $this->redirect(['action' => 'index', $id]);
            }
            $this->Flash->error(__('No guardado.'));
	    }
        $this->set('denuncia', $denuncia);
	}

	public function isAuthorized($user)
	{
	    // All registered users can add articles
	    if ($this->request->action === 'index' || $this->request->action === 'informacion') {
	        return true;
	    }

	    if (isset($user['role']) && $user['role'] === 'inspector'){
	    	return false;
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
