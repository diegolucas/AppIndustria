package diegolucas.com.br.appindustria.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import diegolucas.com.br.appindustria.Uteis.DatabaseUtil;
import diegolucas.com.br.appindustria.model.FlorestaModel;

public class FlorestaRepository {

    DatabaseUtil databaseUtil;

    /***
     * CONSTRUTOR
     * @param context
     */

    public FlorestaRepository(Context context){
        databaseUtil = new DatabaseUtil(context);
    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param florestaModel
     */

    public void Salvar(FlorestaModel florestaModel){

        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("rf_ano",       florestaModel.getAno());
        contentValues.put("rf_estado",   florestaModel.getEstado());
        contentValues.put("rf_cortadas",       florestaModel.getCortadas());
        contentValues.put("rf_volume", florestaModel.getVolume());
        contentValues.put("rf_repostas",florestaModel.getRepostas());
        contentValues.put("rf_pago",      florestaModel.getPago());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_floresta",null,contentValues);
    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param florestaModel
     */
    public void Atualizar(FlorestaModel florestaModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("rf_ano",       florestaModel.getAno());
        contentValues.put("rf_estado",   florestaModel.getEstado());
        contentValues.put("rf_cortadas",       florestaModel.getCortadas());
        contentValues.put("rf_volume", florestaModel.getVolume());
        contentValues.put("rf_repostas",florestaModel.getRepostas());
        contentValues.put("rf_pago",      florestaModel.getPago());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_floresta", contentValues, "id_arvore = ?", new String[]{Integer.toString(florestaModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_floresta","id_arvore = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public FlorestaModel GetFlorestaModel(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_floresta WHERE id_arvore= "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UM NOVO REGISTRO
        FlorestaModel florestaModel =  new FlorestaModel();

        //ADICIONANDO OS DADOS DO REGISTRO
        florestaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_arvore")));
        florestaModel.setAno(cursor.getInt(cursor.getColumnIndex("rf_ano")));
        florestaModel.setEstado(cursor.getString(cursor.getColumnIndex("rf_estado")));
        florestaModel.setCortadas(cursor.getInt(cursor.getColumnIndex("rf_cortadas")));
        florestaModel.setVolume(cursor.getInt(cursor.getColumnIndex("rf_volume")));
        florestaModel.setRepostas(cursor.getInt(cursor.getColumnIndex("rf_repostas")));
        florestaModel.setPago(cursor.getInt(cursor.getColumnIndex("rf_pago")));

        //RETORNANDO O REGISTRO
        return florestaModel;

    }

    /***
     * CONSULTA TODAS OS REGISTROS CADASTRADAS NA BASE
     * @return
     */
    public List<FlorestaModel> SelecionarTodos(){

        List<FlorestaModel> floresta = new ArrayList<FlorestaModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_arvore,      ");
        stringBuilderQuery.append("        rf_ano,        ");
        stringBuilderQuery.append("        rf_estado,    ");
        stringBuilderQuery.append("        rf_cortadas,        ");
        stringBuilderQuery.append("        rf_volume,  ");
        stringBuilderQuery.append("        rf_repostas, ");
        stringBuilderQuery.append("        rf_pago        ");
        stringBuilderQuery.append("  FROM  tb_floresta       ");
        stringBuilderQuery.append(" ORDER BY rf_estado       ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        FlorestaModel florestaModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UM NOVO REGISTRO */
            florestaModel =  new FlorestaModel();

            //ADICIONANDO OS DADOS
            florestaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_arvore")));
            florestaModel.setAno(cursor.getInt(cursor.getColumnIndex("rf_ano")));
            florestaModel.setEstado(cursor.getString(cursor.getColumnIndex("rf_estado")));
            florestaModel.setCortadas(cursor.getInt(cursor.getColumnIndex("rf_cortadas")));
            florestaModel.setVolume(cursor.getInt(cursor.getColumnIndex("rf_volume")));
            florestaModel.setRepostas(cursor.getInt(cursor.getColumnIndex("rf_repostas")));
            florestaModel.setPago(cursor.getInt(cursor.getColumnIndex("rf_pago")));

            //ADICIONANDO NA LISTA
            floresta.add(florestaModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return floresta;

    }
}
