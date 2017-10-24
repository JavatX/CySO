<!--
<div class="contentgrid">
    <ul>
    <?php foreach ($construcciones as $cons):?>
        <li>
            <ul>
                <li>
                    <h1><?= $cons->idConstruccion; ?>
                    </h1>
                </li>
                <li>
                    <h2><?php echo $cons->direccion; ?></h2>
                </li>
                <li>
                    <h2><?= $this->Html->link('Ver',
                        ['controller' => 'Construcciones', 'action' => 'informacion', $cons->idConstruccion]) ?></h2>
                </li>
                <li>
                    <h2><?= $this->Html->link('Denunciar',
                        ['controller' => 'Denuncias', 'action' => 'add', $cons->idConstruccion]) ?></h2>
                </li>
            </ul>
        </li>
        <?php endforeach; ?>
    </ul>
    <div class="botonant">
        <?php echo $this->Paginator->prev(__('Anterior'), array('tag' => false), null)
        ?>
    </div>

    <div class="botonsig">
        <?php echo $this->Paginator->next(__('Siguiente'), array('tag' => false), null)
        ?>
    </div>
    <?php echo $this->Paginator->counter(__('{{page}} de {{pages}}')); ?>
</div>
-->
<div class="contentgrid">
    <table>
        <tr>
            <th><?php echo $this->Paginator->sort('Construcciones.idConstruccion', 'Construcción'); ?></th>
            <th><?php echo $this->Paginator->sort('Construcciones.direccion', 'Dirección'); ?></th>
        </tr>
    <?php foreach ($construcciones as $cons):?>
        <tr>
            <td>
                <h1><?= $cons->idConstruccion; ?>
                </h1>
            </td>
            <td>
                <h2><?php echo $cons->direccion; ?></h2>
            </td>
            <td>
                <h2><?= $this->Html->link('Ver',
                    ['controller' => 'Construcciones', 'action' => 'informacion', $cons->idConstruccion]) ?></h2>
                <h2><?= $this->Html->link('Denunciar',
                    ['controller' => 'Denuncias', 'action' => 'add', $cons->idConstruccion]) ?></h2>
            </td>
        </tr>
        <?php endforeach; ?>
    </table>
    <div class="botonant">
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
