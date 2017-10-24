<div class="contentinfo">
    <div class="informacion">
        <h1><?= h($construccion->idConstruccion) ?>
        <?php if($isAuth): 
            echo $this->Html->link('Modificar',
                    ['controller' => 'Construcciones', 'action' => 'informacion', $construccion->idConstruccion]);
        endif?>
        </h1>
        <div id="info">
            <div id="data">
                <p><h3><?php echo __('Descripción');?>:</h3><?php echo $construccion->descripcion?></p>
                <p><h3><?php echo __('Dirección');?>:</h3><?php echo $construccion->direccion?></p>
                <p><h3><?php echo __('Superficie');?>:</h3><?php echo $construccion->superficie?>m2</p>
                <p><h3><?php echo __('Constructor');?>:</h3><h4><?php echo __('NIF');?>:</h4> <?php echo $construccion->Constructor_Usuario_NIF?><h4><?php echo __('Nombre');?>:</h4>
                    <?= $username->nombre . " " . $username->apellidos;?></p>
                <p><h3><?php echo __('Licencias:  ');?>
                    <?php if($isIns): 
                            echo $this->Html->link('Modificar',
                    ['controller' => 'Construcciones', 'action' => 'modLicencias', $construccion->idConstruccion]);
                    endif?></h3>
                    <ul>
                    <?php foreach ($licencias as $li): ?>
                        <li>
                        <h2><?= $this->Html->link($li->numLicencia,
                    ['controller' => 'Licencias', 'action' => 'informacion', $li->numLicencia, $construccion->idConstruccion]) ?></h2>
                        </li>
                    <?php endforeach ?>
                    </ul>
                </p><br><br>
                <p><h3><?php echo __('Exigencias:');?>
                    <?php if($isIns): 
                            echo $this->Html->link('Modificar',
                    ['controller' => 'Construcciones', 'action' => 'modExigencias', $construccion->idConstruccion]);
                    endif?>
                </h3>
                    <h4><?php echo __('Cumplidas');?>:</h4>
                    <ul>
                    <?php foreach ($exigencias as $ex): ?>
                        <?php if ($ex->cumplimiento == 1) { ?>
                        <li>
                        <h2><?= $this->Html->link($ex->Exigencia_codigo,
                    ['controller' => 'Exigencias', 'action' => 'informacion', $ex->Exigencia_codigo, $construccion->idConstruccion]) ?></h2>
                        </li>
                        <?php }?>
                    <?php endforeach ?>
                    </ul>
                    <br><br>
                    <h4><?php echo __('Incumplidas');?>:</h4>
                    <ul>
                    <?php foreach ($exigencias as $ex): ?>
                        <h2><?php if ($ex->cumplimiento != 1) { ?>
                                <?= $this->Html->link($ex->Exigencia_codigo,
                    ['controller' => 'Exigencias', 'action' => 'informacion', $ex->Exigencia_codigo, $construccion->idConstruccion]) ?><h2>
                        <?php }?>
                    <?php endforeach ?>
                </ul>
                </p>
                </div>
            </div>
        </div>
    </div>
</div>