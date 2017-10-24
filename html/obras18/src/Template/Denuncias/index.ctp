
<!--<div class="contentgrid">
    <?php foreach ($denuncias as $cons):?>
    <div class="cons">
        <div class="imagen">
            <?php echo "<img src=$cons->foto>";?>
            
            <div class="mask"></div>
            <div class="datos">
                <p>Descripción: <br><?= $cons->descripcion;?>.</p>
                <a class="mas" href="infoobra.html">Ver</a>
            </div>
        </div>
        <?php $num = "DN" . $cons->numDenuncia;?>
        <h1><?= $this->Html->link($num,
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
-->
<div class="contentgridD">
    <table>
        <tr>
            <th><?php echo $this->Paginator->sort('Denuncias.numDenuncia', 'Número de denuncia'); ?></th>
            <th><?php echo $this->Paginator->sort('Denuncias.construcciones_idConstruccion', 'Construccion'); ?></th>
            <th><?php echo $this->Paginator->sort('Denuncias.fecha', 'Fecha'); ?></th>
        </tr>
    <?php foreach ($denuncias as $cons):?>
        <tr>
            <td>
                <h1><?= $cons->numDenuncia; ?>
                </h1>
            </td>
            <td>
                <h2><?php echo $cons->construcciones_idConstruccion; ?></h2>
            </td>
            <td>
                <h2><?php echo $cons->fecha; ?></h2>
            </td>
            <td>
                <h2><?= $this->Html->link('Ver',
                    ['controller' => 'Denuncias', 'action' => 'informacion', $cons->numDenuncia]) ?></h2>
            </td>
        </tr>
        <?php endforeach; ?>
    </table>
    <div class="botonantD">
        <?php echo $this->Paginator->prev(__('<< Anterior'), array('tag' => false), null)
        ?>
        <?php echo $this->Paginator->counter(__('   |   {{page}} de {{pages}}   |   ')); ?>
        <?php echo $this->Paginator->next(__('Siguiente >>'), array('tag' => false), null)
        ?>
    </div>

<!--
    <div class="botonsig">
        <?php echo $this->Paginator->next(__('Siguiente'), array('tag' => false), null)
        ?>
    </div>
    <?php echo $this->Paginator->counter(__('{{page}} de {{pages}}')); ?>
-->
</div>