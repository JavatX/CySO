<div class="contentinfo">
    <div class="informacion">
        <?php 
        date_default_timezone_set('UTC');
        echo $this->Form->create();
        ?>
        <p>
        <?php 
        $cont = 0;
        $es = 0;
        $nota = "";
        $cm = 0;
        foreach ($exigencias as $ex) {
            // echo "<div>";
            echo $ex->codigo;
            echo $this->Form->hidden('codigo' . $cont, ['value' => $ex->codigo]);
            foreach ($cumple as $c) {
                if($ex->codigo === $c->Exigencia_codigo):
                    $es = 1;
                    if ($c->cumplimiento == 1):
                        echo " Cumplida";
                        $cm = 1;
                    else:
                        echo " No cumplida";
                        $cm = 0;
                    endif;
                    $nota = $c->notas;
                    break;
                endif;
            }
            if($es == 1):
                if($cm == 1):
                    echo $this->Form->checkbox($cont, ['checked' => true]);
                else:
                    echo $this->Form->checkbox($cont);
                endif;
            else: 
                echo $this->Form->checkbox($cont);
            endif;
            $es = 0;
            ?><br>
            <?php
            echo $this->Form->text('notas' . $cont, ['value' => $nota, 'size' => 155]);
            $nota = "";
            $cont++;
            ?>
            <br><br>
            <?php
            // echo "</div>";
        }?>
        </p>
            <div>
                <?= $this->Form->button('Enviar'); ?>
            </div>
        <?= $this->Form->end(); ?>
    </div>
</div>