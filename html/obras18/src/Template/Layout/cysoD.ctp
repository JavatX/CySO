<?php
/**
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright     Copyright (c) Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link          http://cakephp.org CakePHP(tm) Project
 * @since         0.10.0
 * @license       http://www.opensource.org/licenses/mit-license.php MIT License
 */

$cakeDescription = 'CakePHP: the rapid development php framework';
?>
<!DOCTYPE html>
<html class="hinterior">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CySo - Sistema de Gestión y Seguimiento de Obras</title>
    <script type="text/javascript" src="/html/obras/webroot/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="/html/obras/webroot/js/jsplug.js"></script>
    <?php
    echo $this->Html->css('/webroot/css/interior.css');
    echo $this->Html->css('/webroot/css/sidebar.css');
    ?>
</head>
<body class="interior">
    <?php if($isAuth): ?>
        <div class="containerobraDA">
    <?php else: ?>
        <?php if($isCons): ?>
            <div class="containerobraDC">
        <?php else: ?>
            <div class="containerobraD">
        <?php endif; ?>
    <?php endif; ?>
        <?php if($isAuth): ?>
            <div id="sidebarA">
            <?php else: ?>
            <div id="sidebar">
            <?php endif; ?>
                <ul>
                    <li>
                        <div>
                            <a href="#">
                                <img src="/html/obras/webroot/img/cons.png" alt="Construcciones">
                                Contrucciones
                            </a>
                        </div>                  
                    </li>
                    <li><a href="/html/obras/construcciones/index">Obras</a></li>
                    <?php if($isAuth): ?>
                    <li><a href="/html/obras/construcciones/add">Añadir Obra</a></li>
                    <?php endif; ?>
                    <li><a href="/html/obras/constructores/index">Constructores</a></li>
                </ul>
            </div>
            <?php if($isAuth): ?>
            <div id="sidebar2A">
            <?php else: ?>
            <div id="sidebar2">
            <?php endif; ?>
                <ul>
                    <li>
                        <div>
                            <a href="#">
                                <img src="/html/obras/webroot/img/den.png" alt="Denuncias">
                                Denuncias
                            </a>
                        </div>                  
                    </li>
                    <li><a id="enviadas" href="/html/obras/denuncias/index">Enviadas</a></li>
                    <li><a href="/html/obras/construcciones/lista_construcciones">Denunciar</a></li>
                </ul>
            </div>
            <?php if($isAuth): ?>
            <div id="sidebar3">
                    <ul>
                        <li>
                            <div>
                                <a href="#">
                                    <img src="/html/obras/webroot/img/users.png" alt="Denuncias">
                                    Usuarios
                                </a>
                            </div>                  
                        </li>
                        <li><a href="/html/obras/usuarios/add">Añadir</a></li>
                    </ul>
            </div>
            <?php endif; ?>
                <div class="main-content">
                    <nav class="navegadorobra navegadorobraD">
                        <ul>
                            <li>
                                <?php if($isAuth): ?>
                                <a data-toggle=".containerobraDA" class="sidebar-toggle">
                                <?php else: ?>
                                    <?php if($isCons): ?>
                                        <a data-toggle=".containerobraDC" class="sidebar-toggle">
                                    <?php else: ?>                       
                                        <a data-toggle=".containerobraD" class="sidebar-toggle">
                                    <?php endif; ?>
                            <?php endif; ?>
                                    <span class="bar"></span>
                                    <span class="bar"></span>
                                    <span class="bar"></span>
                                </a>
                            </li>
                            <li>
                                <?php if($isAuth): ?>
                            <a data-toggle=".containerobraDA" class="menu">Menú</a>
                                <?php else: ?>
                                    <?php if($isCons): ?>
                                        <a data-toggle=".containerobraDC" class="menu">Menú</a>
                                    <?php else: ?>                       
                                        <a data-toggle=".containerobraD" class="menu">Menú</a>
                                    <?php endif; ?>
                            <?php endif; ?>
                            </li>
                            <li>
                                <?php if($logged_in): ?>
                                </a><?php echo $this->Html->link('Salir', array('controller' => 'Usuarios', 'action'=>'logout' )); ?><a id='NIF'><?php echo $usuarios_NIF; ?>
                            <?php else: ?>
                            <!--<a id="izqM" href="/html/obras/usuarios/login" class="acceso">Acceder</a>-->
                                <?php echo $this->Html->link('Acceder', array('controller' => 'Usuarios', 'action'=>'login' )); ?>
                            <?php endif; ?>
                                <!--<a href="/html/obras/usuarios/login" class="acceso">Acceder</a>-->
                            </li>
                            <?php if(!$isAuth || !$isCons): ?>
                            <li>
                                <a href="/html/obras/construcciones/lista_construcciones" class="acceso">Denunciar</a>
                            </li>
                            <?php endif; ?>
                            <li>
                                <a id="izq" href="/html/obras/usuarios/index">
                                    <div class="logo">
                                        <img src="/html/obraswebroot/img/logo.png" alt="Inicio">
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="swipe-area"></div>
                    <?php echo $this->fetch('content'); ?>
                </div>
            </div>
        </div>
    </div>
</body>
</html>