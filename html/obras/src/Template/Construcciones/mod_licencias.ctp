<div class="contentinfo">
    <div class="informacion">
        <?php 
        date_default_timezone_set('UTC');
        echo $this->Form->create();
        ?>
        <p>
        <?php 
        $cont = 0;
        foreach ($licencias as $li) {
            echo $li->numLicencia;
                echo $this->Form->checkbox($cont, ['checked' => true]);
                $cont++;
                ?><br><?php
        }?>
        </p>
        <!-- 
            <p>
                <?php 
                echo $this->Form->label('idConstruccion', 'ID Construcción:');
                ?><br><?php
                echo $this->Form->text('idConstruccion'); ?>
            </p>
            <p>
                <?php 
                echo $this->Form->label('direccion', 'Direccion:');
                ?><br><?php
                echo $this->Form->text('direccion'); ?>
            </p>
            <p>
                <?php 
                echo $this->Form->label('superficie', 'Superficie (en m2):');
                ?><br><?php
                echo $this->Form->text('superficie'); ?>
            </p>
            <p>
                <?php echo $this->Form->label('descripcion', 'Descripción:');?><br>
                <?php echo $this->Form->textarea('descripcion', ['rows' => '5', 'cols' => '50']);?>
            </p>
            <p>
                <?php echo $this->Form->label('Constructor_Usuario_NIF', 'Constructor:');?><br>
                <?php echo $this->Form->select('Constructor_Usuario_NIF', $arrayNIF);?>
            </p> -->
            <div>
                <?= $this->Form->button('Enviar'); ?>
            </div>
        <?= $this->Form->end(); ?>
    </div>
</div>