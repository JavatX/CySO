<div class="contentinfo">
    <div class="informacion">
        <h1><?= h($exigencia->codigo) ?><?php echo " " ?><?= h($exigencia->nombre) ?></h1>
        <div id="info">
            <div id="data">
                <p>
                    <h3><?php echo __('Descripción');?>:</h3>
                    <?php echo $exigencia->descripcion?>
                </p>
                <p>
                    <?php if ($cumple->cumplimiento == 1){
                        $c = " cumple ";
                    }else{
                        $c = " no cumple ";
                    } ?>
                    <h4>
                        <?php echo __("La construcción con código " . $cumple->Construccion_idConstruccion . " " . $c . "la exigencia.");?><br>
                    </h4>
                    <h3>Notas:</h3>
                    <?= $cumple->notas ?>
                </p>
            </div>            
        </div>
    </div>
</div>