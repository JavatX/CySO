<div class="contentinfo">
    <div class="informacion">
        <h1><?php if(isset($obras)) echo __('Licencia de obras'); else echo __('Licencia de ocupación'); echo " "; echo __($licencia->numLicencia);?></h1>
        <div id="info">
            <div id="data">
                
                <p>
                    <?php if (isset($obras)){
                        echo "<h3>Tipo</h3>";
                        echo __($obras->tipo);
                        echo "<h3>Subtipo</h3>";
                        echo __($obras->subtipo);
                    }else{
                        echo "<h3>Motivo</h3>";
                        echo __($ocupacion->motivo);
                        echo "<h3>Via</h3>";
                        echo __($ocupacion->via);
                        echo "<h3>Metros</h3>";
                        echo __($ocupacion->metros . "m");
                        echo "<h3>Horario</h3>";
                        $time1 = date_create($ocupacion->horaInicio);
                        $time2 = date_create($ocupacion->horaFin);
                        echo __(date_format($time1, 'H:i') . " - " . date_format($time2, 'H:i'));
                    } ?>
                </p>
            </div>            
        </div>
    </div>
</div>