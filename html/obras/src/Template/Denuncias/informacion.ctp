<div class="contentinfo">
    <div class="informacion">
        <h1><?= h($denuncia->numDenuncia) ?></h1>
        <div id="info">
            <div id="data">
                <p>Fecha: <?php echo $denuncia->fecha?></p>
                <p><?php echo __('Descripción');?>:<br><?php echo $denuncia->descripcion?></p>
                <p>Foto:<br>
                    <div class="fotoDenuncia">
                    <?php echo "<img src=$denuncia->foto>";?>
                    </div>
                </p>
                </div>
            </div>
        </div>
    </div>
</div>