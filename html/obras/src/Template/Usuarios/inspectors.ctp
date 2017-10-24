<div class="contentgridU">
    <table>
        <tr>
            <th><?php echo $this->Paginator->sort('Usuarios.NIF', 'DNI'); ?></th>
            <th><?php echo $this->Paginator->sort('Usuarios.nombre', 'Nombre'); ?></th>
            <th><?php echo $this->Paginator->sort('Usuarios.apellidos', 'Apellidos'); ?></th>
        </tr>
    <?php foreach ($usuarios as $cons):?>
        <tr>
            <td>
                <h1><?= $cons->NIF; ?>
                </h1>
            </td>
            <td>
                <h2><?php echo $cons->nombre; ?></h2>
            </td>
            <td>
                <h2><?php echo $cons->apellidos; ?></h2>
            </td>
            <td>
                <h2><?= $this->Html->link('Asignaciones',
                    ['controller' => 'Denuncias', 'action' => 'asignation', $cons->NIF]) ?></h2>
                <h2><?= $this->Html->link('Modificar',
                    ['controller' => 'Usuarios', 'action' => 'mod', $cons->NIF]) ?></h2>
            </td>
        </tr>
        <?php endforeach; ?>
    </table>
    <div class="botonantU">
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