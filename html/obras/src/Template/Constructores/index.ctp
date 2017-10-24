<div class="contentgrid">
    <table>
        <tr>
            <th><?php echo $this->Paginator->sort('Constructores.idConstructor', 'Constructor'); ?></th>
            <th><?php echo $this->Paginator->sort('Constructores.Usuario_NIF', 'DNI'); ?></th>
        </tr>
    <?php foreach ($constructores as $cons):?>
        <tr>
            <td>
                <h1><?= $cons->idConstructor; ?>
                </h1>
            </td>
            <td>
                <h2><?php echo $cons->Usuario_NIF; ?></h2>
            </td>
            <td>
                <?php if($isAuth || ($isCons && $nifC === $cons->Usuario_NIF)):?>
                <h2><?= $this->Html->link('Modificar',
                    ['controller' => 'Usuarios', 'action' => 'mod', $cons->Usuario_NIF]) ?></h2>
                <?php endif; ?>
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
