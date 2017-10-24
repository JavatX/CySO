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