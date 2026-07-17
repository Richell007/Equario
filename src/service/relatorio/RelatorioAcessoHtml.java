package service.relatorio;

public class RelatorioAcessoHtml extends RelatorioAcesso {

    @Override
    protected String criarCabecalho() {
        return "<html><body><h1>Relatório de Acessos</h1>";
    }

    @Override
    protected String criarRodape() {
        return "</body></html>";
    }

    @Override
    protected String formatarLinhaResumo(int totalAcessos, long usuariosUnicos) {
        return "<p>Total de acessos: " + totalAcessos + "<br>Usuários únicos: " + usuariosUnicos + "</p>";
    }

    @Override
    protected String abrirListaUsuarios() {
        return "<ul>";
    }

    @Override
    protected String fecharListaUsuarios() {
        return "</ul>";
    }

    @Override
    protected String formatarLinhaUsuario(String nomeUsuario, long quantidadeAcessos) {
        return "<li>" + nomeUsuario + ": " + quantidadeAcessos + " acesso(s)</li>";
    }
}