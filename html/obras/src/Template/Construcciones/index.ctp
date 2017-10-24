
<div class="contentgrid">
    <?php foreach ($construcciones as $cons):?>
    <div class="cons">
        <div class="imagen">
            <img src="../webroot/img/img1.jpg">
            <div class="mask"></div>
            <div class="datos">
                <p>Descripción: <br><?= $cons->descripcion;?>.</p>
                <a class="mas" href="infoobra.html">Ver</a>
            </div>
        </div>
        <h1><?= $this->Html->link($cons->idConstruccion,
            ['controller' => 'Construcciones', 'action' => 'informacion', $cons->idConstruccion]) ?>
        </h1>
    </div>
    <?php endforeach; ?>
    
    <div class="botonant">
        <?php echo $this->Paginator->prev(__('Anterior'), array('tag' => false), null)
        ?>
    </div>

    <div class="botonsig">
        <?php echo $this->Paginator->next(__('Siguiente'), array('tag' => false), null)
        ?>
    </div>
</div>
