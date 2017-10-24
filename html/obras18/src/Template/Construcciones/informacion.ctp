<div class="contentinfo">
    <div class="informacion">
        <h1><?= h($construccion->idConstruccion) ?></h1>
        <div id="info">
            <div id="data">
                <p><?php echo __('Descripción');?>:<br><?php echo $construccion->descripcion?></p>
                <p>Dirección: <?php echo $construccion->direccion?></p>
                <p>Superficie: <?php echo $construccion->superficie?>m2</p>
                <p>Constructor:<br>ID: <?php echo $construccion->Constructor_Usuario_NIF?><br>Nombre: 
                    <?= $username->nombre . " " . $username->apellidos;?></p>
                <p>Licencias:<br>---</p>
                <p>Exigencias:<br>---</p>
                </div>
            </div>
        </div>
        <div>
            
        </div>
    </div>
</div>