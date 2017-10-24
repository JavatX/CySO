<?php
/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link      http://cakephp.org CakePHP(tm) Project
 * @since     0.2.9
 * @license   http://www.opensource.org/licenses/mit-license.php MIT License
 */
namespace App\Controller;

use Cake\Controller\Controller;
use Cake\Event\Event;

/**
 * Application Controller
 *
 * Add your application-wide methods in the class below, your controllers
 * will inherit them.
 *
 * @link http://book.cakephp.org/3.0/en/controllers.html#the-app-controller
 */
class AppController extends Controller
{

    /**
     * Initialization hook method.
     *
     * Use this method to add common initialization code like loading components.
     *
     * e.g. `$this->loadComponent('Security');`
     *
     * @return void
     */
    public function initialize()
    {
        parent::initialize();

        $this->loadComponent('RequestHandler');
        $this->loadComponent('Flash');
        $this->loadComponent('Auth', [
            'authenticate' => [
                'Form' => [
                    'fields' => [
                    'username' => 'NIF',
                    'password' => 'contraseña'],
		    'userModel' => 'Usuarios'
                ]
            ],
            'loginAction' => [
                'controller' => 'Usuarios',
                'action' => 'login'
            ],
            'loginRedirect' => [
                'controller' => 'Usuarios',
                'action' => 'index'
            ],
            'logoutRedirect' => [
                'controller' => 'Usuarios',
                'action' => 'index'
            ]
        ]);
    }

    public function beforeFilter(Event $event)
    {
        $this->Auth->allow(['index', 'view', 'add', 'informacion', 'buscar', 'listaConstrucciones']);
        $this->set('logged_in', $this->_loggedIn());
        $this->set('usuarios_NIF', $this->_usersUsername());
        $this->set('isAuth', $this->_isAuthorized());
        $this->set('isCons', $this->_isConstructor());
    }
    /**
     * Before render callback.
     *
     * @param \Cake\Event\Event $event The beforeRender event.
     * @return void
     */
    public function beforeRender(Event $event)
    {
        if (!array_key_exists('_serialize', $this->viewVars) &&
            in_array($this->response->type(), ['application/json', 'application/xml'])
        ) {
            $this->set('_serialize', true);
        }
    }

    function _isAuthorized()
    {
        $isAuth = false;
        // Admin can access every action
        if ($this->Auth->user('tipo') == "admin") {
            $isAuth = true;
        }
        return $isAuth;
    }

    function _isConstructor()
    {
        $isCons = false;
        // Admin can access every action
        if ($this->Auth->user('tipo') == "cons") {
            $isCons = true;
        }
        return $isCons;
    }

    function _loggedIn(){
        $logged_in = false;
        if ($this->Auth->user()){
            $logged_in = true;
        }
        return $logged_in;
    }

    function _usersUsername(){
        $usuarios_NIF = null;
        if($this->Auth->user()){
            $usuarios_NIF = $this->Auth->user('nombre');
        }
        return $usuarios_NIF;
    }
}
