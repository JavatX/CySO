<?php
$this->Paginator->options(array('update' => '#content'))
?>
<div class="contentgrid" id="content">
    <?php foreach ($constructores as $cons):?>
    <div class="cons">
        <div class="imagen">
            <img src="/html/obras/webroot/img/cons.jpg">
            <div class="mask"></div>
            <div class="datos">
                <?= $this->Html->link('Ver', ['controller' => 'Constructores', 'action' => 'informacion', $cons->Usuario_NIF]) ?>
            </div>
        </div>
        <!--<h1><a href="infoobra.html"><?php echo $cons->idConstructor?></a></h1>-->
        <h1><?= $this->Html->link($cons->idConstructor,
            ['controller' => 'Constructores', 'action' => 'informacion', $cons->Usuario_NIF]) ?>
        </h1>

    </div>
    <?php endforeach; ?>
</div>
<div>
    <div class="botonant">
        <?php echo $this->Paginator->prev(__('Anterior'), array('tag' => false), null)
        ?>
    </div>

    <div class="botonsig">
        <?php echo $this->Paginator->next(__('Siguiente'), array('tag' => false), null)
        ?>
    </div>
</div>
