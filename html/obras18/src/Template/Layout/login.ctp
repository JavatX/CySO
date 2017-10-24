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
    echo $this->Html->css('/webroot/css/ppal.css');
    echo $this->Html->css('/webroot/css/login.css');
    ?>
</head>
<body>
    <body>
        <div class="containerAc">
            <div class="main-content">
                <nav class="navegador">
                    <ul>
                        <li>
                            <a id="izq" href="/html/obras/usuarios/index">
                                <div class="logo">
                                    <img src="../webroot/img/logo.png" alt="Construcciones">
                                </div>
                            </a>
                        </li>
                    </ul>
                </nav>
                <?php echo $this->fetch('content'); ?>
            </div>
        </div>
    </body>
</html>