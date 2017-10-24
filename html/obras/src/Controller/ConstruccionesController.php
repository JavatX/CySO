<?php

namespace App\Controller;

class ConstruccionesController extends AppController
{

	public $paginate = [
		'limit' => 20,
		'order' => [
			'Construcciones.idConstruccion' => 'asc'
			]
	];

	public function index()
	{
		$this->viewBuilder()->layout('cysoC');
		$this->set('construcciones', $this->paginate());
	}

	public function add(){
		$this->viewBuilder()->layout('cysoC');
		$this->loadModel('Usuarios');
		$arrayNIF = array();
		$aux = $this->Usuarios->find('all')->where(['tipo LIKE' => 'cons'])->toArray();
		for ($i=0; $i < count($aux); $i++) { 
			$arrayNIF[$aux[$i]['NIF']] = $aux[$i]['NIF'];
		}
		$this->set(compact('arrayNIF'));
		$this->set('usuarios', $this->Usuarios->find()->select(['NIF'])->where(['tipo LIKE' => 'cons']));
		$construccion = $this->Construcciones->newEntity();
		if ($this->request->is('post')) {
            $construccion = $this->Construcciones->patchEntity($construccion, $this->request->data);
            if ($this->Construcciones->save($construccion)) {
                $this->Flash->success(__('Guardado.'));
                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('No guardado.'));
	    }
        $this->set('construccion', $construccion);
	}
	public function listaConstrucciones()
	{
		$this->viewBuilder()->layout('cysoC');
		$this->set('construcciones', $this->paginate());
	}

	public function modLicencias($id = null){
		$this->viewBuilder()->layout('cysoC');
		$construccion = $this->Construcciones->get($id);
		$this->loadModel('Licencias');
		$licencias = $this->Licencias->find('all')->where(['Construcciones_idConstruccion LIKE' => $id]);
		$this->set('licencias', $licencias);
		if ($this->request->is('post')) {
			$data = $this->request->data;
			$arL = $licencias->toArray();
			$count = 0;
			foreach ($data as $d) {
				if($d == 0):
					$entity = $this->Licencias->get($arL[$count]->numLicencia);
					$result = $this->Licencias->delete($entity);
					// echo $arL[$count]->numLicencia;
				endif;
				$count++;
			}
            return $this->redirect(['action' => 'informacion', $id]);

        }
	}

	public function modExigencias($id = null){
		$this->viewBuilder()->layout('cysoC');
		$construccion = $this->Construcciones->get($id);
		$this->loadModel('Exigencias');
		$exigencias = $this->Exigencias->find('all');
		$this->loadModel('Cumple');
		$cumple = $this->Cumple->find('all')->where(['Construccion_idConstruccion LIKE' => $id]);
		$this->set('cumple', $cumple);
		$this->set('exigencias', $exigencias);
		if ($this->request->is('post')) {
			$data = $this->request->data;
			$arC = $cumple->toArray();
			printf($data['notas14']);
			print_r($this->request->data);
			// $arL = $exigencias->toArray();
			// $count = 0;
			print_r(count($data));
			for($i = 0; $i < count($data)/3; $i++){
				if($data[$i] == 1):
					$exi = $this->Cumple->newEntity();
					$exi->Construccion_idConstruccion = $id;
					$exi->Exigencia_codigo = $data['codigo' . $i];
					$exi->notas = $data['notas' . $i];
					$exi->cumplimiento = $data[$i];
					$this->Cumple->save($exi);
				endif;
				//modificación
				foreach ($arC as $a) {
					if($a->Exigencia_codigo === $data['codigo' . $i]):
						$a->cumplimiento = $data[$i];
						$a->notas = $data['notas' . $i];
						$this->Cumple->save($a);
					endif;
				}
				// $data['codigo' . $i];
			}
			// foreach ($data as $d) {
				// if($d == 0):
					// $entity = $this->Licencias->get($arL[$count]->numLicencia);
					// $result = $this->Licencias->delete($entity);
					// echo $arL[$count]->numLicencia;
				// endif;
				// $count++;
			// }
            return $this->redirect(['action' => 'informacion', $id]);

        }
	}
	public function informacion($id = null)
	{
		$this->viewBuilder()->layout('cysoC');
		$construccion = $this->Construcciones->get($id);
		$this->loadModel('Usuarios');
		$NIF = $construccion['Constructor_Usuario_NIF'];
		$nombre = $this->Usuarios->get($NIF);
		$this->loadModel('Cumple');
		$exigencias = $this->Cumple->find('all')->where(['Construccion_idConstruccion LIKE' => $id]);
		$this->loadModel('Licencias');
		$licencias = $this->Licencias->find('all')->where(['Construcciones_idConstruccion LIKE' => $id]);
		$this->set('licencias', $licencias);
		$this->set('exigencias', $exigencias);
		$this->set('username', $nombre);
		$this->set(compact('construccion'));
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
