
<div class="contentgrid">
        <? $num = count($denuncias);?>
        <?php foreach ($denuncias as $cons):?>
        <div class="cons">
            <div class="imagen">
                <?php echo "<img src=$cons->foto>";?>

                <div class="mask"></div>
                <div class="datos">
                    <p>Descripción: <br><?= $cons->descripcion;?>.</p>
                    <?= $this->Html->link('Ver',
            ['controller' => 'Denuncias', 'action' => 'informacion', $cons->numDenuncia]) ?>
                    <!-- <a class="mas" href="infoobra.html">Ver</a> -->
                </div>
            </div>
            <?php $num = "DN" . $cons->numDenuncia;?>
            <h1><?= $this->Html->link($num,
            ['controller' => 'Denuncias', 'action' => 'informacion', $cons->numDenuncia]) ?>
            </h1>
        </div>
        <?php endforeach; ?>
        <?php
            if((count($denuncias) < 3) || (count($denuncias) >= 4 && count($denuncias) < 6) || (count($denuncias) >= 7 && count($denuncias) < 9)):
                $x = 1;
                while ($x <= 2) {?>
                <div class="cons">
                    <div class="imagen">
                        <?php echo "<img=/obras/webroot/img/trans.png>";?>
                    </div>
                </div>
                <?php $x++;} ?>
        <?php endif;?>
    <div class="botonantD">
        <?php echo $this->Paginator->prev(__('<< Anterior'), array('tag' => false), null)
        ?>
        <?php echo $this->Paginator->counter(__('   |   {{page}} de {{pages}}   |   ')); ?>
        <?php echo $this->Paginator->next(__('Siguiente >>'), array('tag' => false), null)
        ?>
    </div>
</div>

<!-- 
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
</div> -->