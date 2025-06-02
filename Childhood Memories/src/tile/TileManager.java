package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    //Nivel 1
    public int[][][] groundLayer1;
    public int[][][] objectsLayer1;
    public int[][][] treesDLayer1;
    public int[][][] treesDeLayer1;

    //Nivel 2
    public int[][][] groundLayer2;
    public int[][][] IarbaApa;
    public int[][][] PodPlanteCasaCopacColiziune;
    public int[][][] CopacCasaNecoliziune;

    //Nivel 3
    public int[][][] groundLayer3;
    public int[][][] IarbaApa3;
    public int[][][] Obiecte3;
    public int[][][] ObiecteDedesubt3;
    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[400]; // Ai acum mai multe tile-uri (ground, obiecte, copaci)
        //Nivel 1
        groundLayer1 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        objectsLayer1 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        treesDLayer1 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        treesDeLayer1 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        //Nivel2
        groundLayer2 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        IarbaApa = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        PodPlanteCasaCopacColiziune = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        CopacCasaNecoliziune = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        //Nivel 3

        groundLayer3 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        IarbaApa3 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        Obiecte3 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        ObiecteDedesubt3 = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        //Nivel 1
        loadMap("/Maps/Nivel1/Ground.txt", groundLayer1, 0);
        loadMap("/Maps/Nivel1/Obiecte.txt", objectsLayer1, 0);
        loadMap("/Maps/Nivel1/Copaci_deasupra.txt", treesDeLayer1, 0);
        loadMap("/Maps/Nivel1/Copaci_dedesubt.txt", treesDLayer1, 0);
        //Nivel 2
        loadMap("/Maps/Nivel2/Niv2_Ground.txt", groundLayer2, 1);
        loadMap("/Maps/Nivel2/Niv2_IarbaApa.txt", IarbaApa, 1);
        loadMap("/Maps/Nivel2/Niv2_PodPlanteCasaCopacColiziune.txt", PodPlanteCasaCopacColiziune, 1);
        loadMap("/Maps/Nivel2/Niv2_CopacNecoliziune.txt", CopacCasaNecoliziune,1);

        //Nivel 3
        loadMap("/Maps/Nivel3/Niv3_Ground.txt", groundLayer3, 2);
        loadMap("/Maps/Nivel3/Niv3_IarbaApa.txt", IarbaApa3, 2);
        loadMap("/Maps/Nivel3/Niv3_Obiecte.txt", Obiecte3, 2);
        loadMap("/Maps/Nivel3/Niv3_ObiecteDedesubt.txt", ObiecteDedesubt3,2);

    }
    public void getTileImage() {
        // Ground tiles
        setup(0, "/Tile_Niv1/Ground/Iarba_Naruto1", false);
        setup(1, "/Tile_Niv1/Ground/Iarba_Naruto2", false);
        setup(2, "/Tile_Niv1/Ground/Iarba_Naruto3", false);
        setup(3, "/Tile_Niv1/Ground/piatra_drum", false);
        setup(43, "/Tile_Niv1/Ground/apa_mij", true);
        setup(44,"/Tile_Niv1/Ground/apa_iarba_sus", true );
        setup(45, "/Tile_Niv1/Ground/apa_colt_dreapta", true);
        setup(46, "/Tile_Niv1/Ground/apa_colt_stanga", true);
        setup(47, "/Tile_Niv1/Ground/apa_colt_dreapta_jos", true);
        setup(48,"/Tile_Niv1/Ground/apa_colt_stanga_jos",true);
        setup(49,"/Tile_Niv1/Ground/apa_stanga",true);
        setup(50,"/Tile_Niv1/Ground/apa_dreapta", true);
        setup(51, "/Tile_Niv1/Ground/apa_iarba_jos", true);

        // Object tiles
        setup(4, "/Tile_Niv1/Obiecte/gard_0", true);
        setup(5, "/Tile_Niv1/Obiecte/gard_2", true);
        setup(6, "/Tile_Niv1/LanPorumb/LanMijMij", true);
        setup(7, "/Tile_Niv1/LanPorumb/LanMijMij", true);
        setup(8, "/Tile_Niv1/LanPorumb/LanMijMij", true);
        setup(9, "/Tile_Niv1/LanPorumb/LanMijMij", true);
        setup(10, "/Tile_Niv1/LanPorumb/LanMijMij", true);
        setup(11, "/Tile_Niv1/Obiecte/gard_1", true);
        setup(12, "/Tile_Niv1/Obiecte/radacina_copac1", true);
        setup(13, "/Tile_Niv1/Obiecte/piatra_decor", true);
        setup(14, "/Tile_Niv1/Obiecte/cactus_0",false);
        setup(15, "/Tile_Niv1/Obiecte/cactus_1", false);
        setup(16, "/Tile_Niv1/Obiecte/cactus_2", true);
        setup(17,"/Tile_Niv1/Obiecte/cactus_3",true);
        setup(18,"/Tile_Niv1/Obiecte/radacina_copac2",true);
        setup(19, "/Tile_Niv1/Obiecte/casa_0", true);
        setup(20,"/Tile_Niv1/Obiecte/casa_1",true);
        setup(21,"/Tile_Niv1/Obiecte/casa_2",true);
        setup(22,"/Tile_Niv1/Obiecte/casa_3",true);
        setup(23,"/Tile_Niv1/Obiecte/casa_4",true);
        setup(24,"/Tile_Niv1/Obiecte/casa_5",true);
        setup(25,"/Tile_Niv1/Obiecte/casa_6",true);
        setup(26,"/Tile_Niv1/Obiecte/casa_7",true);
        setup(27,"/Tile_Niv1/Obiecte/casa_8",true);
        setup(28,"/Tile_Niv1/Obiecte/casa_9",true);
        setup(29,"/Tile_Niv1/Obiecte/casa_10",true);
        setup(30,"/Tile_Niv1/Obiecte/casa_11",true);
        setup(31,"/Tile_Niv1/Obiecte/casa_12",true);
        setup(32,"/Tile_Niv1/Obiecte/casa_13",true);
        setup(33,"/Tile_Niv1/Obiecte/casa_14",true);
        setup(34,"/Tile_Niv1/Obiecte/casa_15",true);
        setup(35,"/Tile_Niv1/Obiecte/casa_16",true);
        setup(36,"/Tile_Niv1/Obiecte/casa_17",true);
        setup(37,"/Tile_Niv1/Obiecte/casa_18",true);

        // Tree tiles
        setup(38,"/Tile_Niv1/Copaci/l0_copaci_124",false);
        setup(39,"/Tile_Niv1/Copaci/l0_copaci_125",false);
        setup(40,"/Tile_Niv1/Copaci/l0_copaci_140",false);
        setup(41,"/Tile_Niv1/Copaci/l0_copaci_141",false);
        setup(42,"/Tile_Niv1/Copaci/l0_copaci_151",true);

        //PoartaMica
        setup(96,"/Tile_Niv1/Obiecte/l0_poartaMica_01",false);
        setup(97,"/Tile_Niv1/Obiecte/l0_poartaMica_02",false);
        setup(98,"/Tile_Niv1/Obiecte/l0_poartaMica_03",false);
        setup(99,"/Tile_Niv1/Obiecte/l0_poartaMica_04",false);
        setup(100,"/Tile_Niv1/Obiecte/l0_poartaMica_05",false);
        setup(101,"/Tile_Niv1/Obiecte/l0_poartaMica_06",false);
        setup(102,"/Tile_Niv1/Obiecte/l0_poartaMica_07",false);
        setup(103,"/Tile_Niv1/Obiecte/l0_poartaMica_08",false);
        setup(104,"/Tile_Niv1/Obiecte/l0_poartaMica_09",false);
        setup(105,"/Tile_Niv1/Obiecte/l0_poartaMica_10",false);
        setup(106,"/Tile_Niv1/Obiecte/l0_poartaMica_11",true);
        setup(107,"/Tile_Niv1/Obiecte/l0_poartaMica_12",true);

        //PoartaMare
        for (int i = 0; i <= 43; i++) {
            int tileIndex = 52 + i;
            if(tileIndex>=66 && tileIndex<=95) {
                setup(tileIndex,"/Tile_Niv1/Obiecte/Poarta" + i, true);
            }
            else {
                setup(tileIndex, "/Tile_Niv1/Obiecte/Poarta" + i, false);
            }
        }

        //Nivel 2

        //Ground
        setup(108,"/Tile_Niv2/Ground/Verde",false);

        //IarbaApa
        setup(109,"/Tile_Niv2/IarbaApa/Apa1",true);
        setup(110,"/Tile_Niv2/IarbaApa/Apa2",true);
        setup(111,"/Tile_Niv2/IarbaApa/Apa3",true);
        setup(112,"/Tile_Niv2/IarbaApa/ColtDreaptaJos",false);
        setup(113,"/Tile_Niv2/IarbaApa/ColtDreaptaSus",false);
        setup(114,"/Tile_Niv2/IarbaApa/ColtStangaJos",false);
        setup(115,"/Tile_Niv2/IarbaApa/ColtStangaSus",false);
        setup(116,"/Tile_Niv2/IarbaApa/Dreapta1",false);
        setup(117,"/Tile_Niv2/IarbaApa/Dreapta2",false);
        setup(118,"/Tile_Niv2/IarbaApa/Iarba",false);
        setup(119,"/Tile_Niv2/IarbaApa/Iarba2",false);
        setup(120,"/Tile_Niv2/IarbaApa/Iarba3",false);
        setup(121,"/Tile_Niv2/IarbaApa/Jos1",false);
        setup(122,"/Tile_Niv2/IarbaApa/Jos2",false);
        setup(123,"/Tile_Niv2/IarbaApa/Dreapta1",false);
        setup(124,"/Tile_Niv2/IarbaApa/Dreapta2",false);
        setup(125,"/Tile_Niv2/IarbaApa/Sus1",false);
        setup(126,"/Tile_Niv2/IarbaApa/Sus2",false);
        setup(127,"/Tile_Niv2/IarbaApa/Stanga1",false);
        setup(128,"/Tile_Niv2/IarbaApa/Stanga2",false);
        setup(129,"/Tile_Niv2/IarbaApa/PamantApaS",true);
        setup(130,"/Tile_Niv2/IarbaApa/PamantApaM",true);
        setup(131,"/Tile_Niv2/IarbaApa/PamantApaM2",true);
        setup(132,"/Tile_Niv2/IarbaApa/PamantApaD",true);
        setup(133,"/Tile_Niv2/IarbaApa/PamantDreapta",true);
        setup(134,"/Tile_Niv2/IarbaApa/PamantMijloc",true);
        setup(135,"/Tile_Niv2/IarbaApa/PamantMijloc2",true);
        setup(136,"/Tile_Niv2/IarbaApa/PamantStanga",true);

        //Pod,Plante,Casa,Copaci
        setup(137,"/Tile_Niv2/PodPlanteCasaCopaci/Casa1",false);
        setup(138,"/Tile_Niv2/PodPlanteCasaCopaci/Casa2",false);
        setup(139,"/Tile_Niv2/PodPlanteCasaCopaci/Casa3",false);
        setup(140,"/Tile_Niv2/PodPlanteCasaCopaci/Casa4",false);
        setup(141,"/Tile_Niv2/PodPlanteCasaCopaci/Casa5",false);
        setup(142,"/Tile_Niv2/PodPlanteCasaCopaci/Casa6",false);
        setup(143,"/Tile_Niv2/PodPlanteCasaCopaci/Casa7",true);
        setup(144,"/Tile_Niv2/PodPlanteCasaCopaci/Casa8",true);
        setup(145,"/Tile_Niv2/PodPlanteCasaCopaci/Casa9",true);
        setup(146,"/Tile_Niv2/PodPlanteCasaCopaci/Ciuperca1",true);
        setup(147,"/Tile_Niv2/PodPlanteCasaCopaci/Ciuperca2",true);
        setup(148,"/Tile_Niv2/PodPlanteCasaCopaci/Ciuperca3",true);
        setup(149,"/Tile_Niv2/PodPlanteCasaCopaci/CopacC1",false);
        setup(150,"/Tile_Niv2/PodPlanteCasaCopaci/CopacC2",false);
        setup(151,"/Tile_Niv2/PodPlanteCasaCopaci/CopacT1",true);
        setup(152,"/Tile_Niv2/PodPlanteCasaCopaci/CopacT2",true);
        setup(153,"/Tile_Niv2/PodPlanteCasaCopaci/CopacelC1",false);
        setup(154,"/Tile_Niv2/PodPlanteCasaCopaci/CopacelC2",false);
        setup(155,"/Tile_Niv2/PodPlanteCasaCopaci/CopacelT1",true);
        setup(156,"/Tile_Niv2/PodPlanteCasaCopaci/CopacelT2",true);
        setup(157,"/Tile_Niv2/PodPlanteCasaCopaci/CopacMic",true);
        setup(158,"/Tile_Niv2/PodPlanteCasaCopaci/Floare1",true);
        setup(159,"/Tile_Niv2/PodPlanteCasaCopaci/Floare2",true);
        setup(160,"/Tile_Niv2/PodPlanteCasaCopaci/Floare3",true);
        setup(161,"/Tile_Niv2/PodPlanteCasaCopaci/PodJosDreapta",true);
        setup(162,"/Tile_Niv2/PodPlanteCasaCopaci/PodJosDreapta1",true);
        setup(163,"/Tile_Niv2/PodPlanteCasaCopaci/PodJosMijloc",true);
        setup(164,"/Tile_Niv2/PodPlanteCasaCopaci/PodMijloc",false);
        setup(165,"/Tile_Niv2/PodPlanteCasaCopaci/PodSusDreapta",true);
        setup(166,"/Tile_Niv2/PodPlanteCasaCopaci/PodSusMijloc",true);
        setup(167,"/Tile_Niv2/PodPlanteCasaCopaci/PodSusStanga",true);
        setup(168,"/Tile_Niv2/PodPlanteCasaCopaci/PodJosStanga1",true);
        setup(169,"/Objects/portal",false);

        //Nivel3
        //Castel
        setup(170,"/Tile_Niv3/Castel/1",false);
        setup(171,"/Tile_Niv3/Castel/2",false);
        setup(172,"/Tile_Niv3/Castel/3",false);
        setup(173,"/Tile_Niv3/Castel/4",false);
        setup(174,"/Tile_Niv3/Castel/5",false);
        setup(175,"/Tile_Niv3/Castel/6",false);
        setup(176,"/Tile_Niv3/Castel/7",false);
        setup(177,"/Tile_Niv3/Castel/8",false);
        setup(178,"/Tile_Niv3/Castel/9",false);
        setup(179,"/Tile_Niv3/Castel/10",false);
        setup(180,"/Tile_Niv3/Castel/11",false);
        setup(181,"/Tile_Niv3/Castel/12",false);
        setup(182,"/Tile_Niv3/Castel/13",true);
        setup(183,"/Tile_Niv3/Castel/14",true);
        setup(184,"/Tile_Niv3/Castel/15",true);
        setup(185,"/Tile_Niv3/Castel/16",true);
        setup(186,"/Tile_Niv3/Castel/17",true);
        setup(187,"/Tile_Niv3/Castel/18",true);
        setup(188,"/Tile_Niv3/Castel/19",true);
        setup(189,"/Tile_Niv3/Castel/20",true);
        setup(190,"/Tile_Niv3/Castel/21",true);
        setup(191,"/Tile_Niv3/Castel/22",true);
        setup(192,"/Tile_Niv3/Castel/23",true);
        setup(193,"/Tile_Niv3/Castel/24",true);
        setup(194,"/Tile_Niv3/Castel/25",true);
        setup(195,"/Tile_Niv3/Castel/26",true);
        setup(196,"/Tile_Niv3/Castel/27",true);
        setup(197,"/Tile_Niv3/Castel/28",true);
        setup(198,"/Tile_Niv3/Castel/29",true);
        setup(199,"/Tile_Niv3/Castel/30",true);

        //Fantana
        setup(200,"/Tile_Niv3/Fantani/Fantana/1",true);
        setup(201,"/Tile_Niv3/Fantani/Fantana/2",true);
        setup(202,"/Tile_Niv3/Fantani/Fantana/3",true);
        setup(203,"/Tile_Niv3/Fantani/Fantana/4",true);
        setup(204,"/Tile_Niv3/Fantani/Fantana/5",true);
        setup(205,"/Tile_Niv3/Fantani/Fantana/6",true);
        setup(206,"/Tile_Niv3/Fantani/Fantana/7",true);
        setup(207,"/Tile_Niv3/Fantani/Fantana/8",true);
        setup(208,"/Tile_Niv3/Fantani/Fantana/9",true);
        for(int index = 209; index < 213; index++)
        {
            setup(index,"/Tile_Niv3/Fantani/Fantana/9", false);
        }
        //IarbaApaGround
        setup(213, "/Tile_Niv3/GroundIarbaApa/_000", false);
        setup(214, "/Tile_Niv3/GroundIarbaApa/_001", false);
        setup(215, "/Tile_Niv3/GroundIarbaApa/_008", true);
        setup(216, "/Tile_Niv3/GroundIarbaApa/_009", false);
        setup(217, "/Tile_Niv3/GroundIarbaApa/_016", true);
        setup(218, "/Tile_Niv3/GroundIarbaApa/_017", true);
        setup(219, "/Tile_Niv3/GroundIarbaApa/_030", false);
        setup(220, "/Tile_Niv3/GroundIarbaApa/_031", false);
        setup(221, "/Tile_Niv3/GroundIarbaApa/_038", false);
        setup(222, "/Tile_Niv3/GroundIarbaApa/_039", false);
        setup(223, "/Tile_Niv3/GroundIarbaApa/_040", false);
        setup(224, "/Tile_Niv3/GroundIarbaApa/_041", false);
        setup(225, "/Tile_Niv3/GroundIarbaApa/_042", false);
        setup(226, "/Tile_Niv3/GroundIarbaApa/_043", false);
        setup(227, "/Tile_Niv3/GroundIarbaApa/_044", false);
        setup(228, "/Tile_Niv3/GroundIarbaApa/_045", false);
        setup(229, "/Tile_Niv3/GroundIarbaApa/_046", false);
        setup(230, "/Tile_Niv3/GroundIarbaApa/_047", false);
        setup(231, "/Tile_Niv3/GroundIarbaApa/_048", false);
        setup(232, "/Tile_Niv3/GroundIarbaApa/_051", false);
        setup(233, "/Tile_Niv3/GroundIarbaApa/_052", true);
        setup(234, "/Tile_Niv3/GroundIarbaApa/_053", true);
        setup(235, "/Tile_Niv3/GroundIarbaApa/_054", false);
        setup(236, "/Tile_Niv3/GroundIarbaApa/_055", false);
        setup(237, "/Tile_Niv3/GroundIarbaApa/_056", false);
        setup(238, "/Tile_Niv3/GroundIarbaApa/_057", false);
        setup(239, "/Tile_Niv3/GroundIarbaApa/_058", false);
        setup(240, "/Tile_Niv3/GroundIarbaApa/_059", false);
        setup(241, "/Tile_Niv3/GroundIarbaApa/_060", false);
        setup(242, "/Tile_Niv3/GroundIarbaApa/_061", false);
        setup(243, "/Tile_Niv3/GroundIarbaApa/_062", false);
        setup(244, "/Tile_Niv3/GroundIarbaApa/_063", true);
        setup(245, "/Tile_Niv3/GroundIarbaApa/_064", true);
        setup(246, "/Tile_Niv3/GroundIarbaApa/_065", true);
        setup(247, "/Tile_Niv3/GroundIarbaApa/_066", false);
        setup(248, "/Tile_Niv3/GroundIarbaApa/_067", false);
        setup(249, "/Tile_Niv3/GroundIarbaApa/_068", false);
        setup(250, "/Tile_Niv3/GroundIarbaApa/_069", false);
        setup(251, "/Tile_Niv3/GroundIarbaApa/_070", false);
        setup(252, "/Tile_Niv3/GroundIarbaApa/_071", false);
        setup(253, "/Tile_Niv3/GroundIarbaApa/_072", false);
        setup(254, "/Tile_Niv3/GroundIarbaApa/_073", true);
        setup(255, "/Tile_Niv3/GroundIarbaApa/_074", false);
        setup(256, "/Tile_Niv3/GroundIarbaApa/_075", false);
        setup(257, "/Tile_Niv3/GroundIarbaApa/_076", false);
        setup(258, "/Tile_Niv3/GroundIarbaApa/_077", false);
        setup(259, "/Tile_Niv3/GroundIarbaApa/_078", true);
        setup(260, "/Tile_Niv3/GroundIarbaApa/_079", true);
        setup(264, "/Tile_Niv3/GroundIarbaApa/_083", false);
        setup(265, "/Tile_Niv3/GroundIarbaApa/_084", false);
        setup(266, "/Tile_Niv3/GroundIarbaApa/_085", false);
        setup(267, "/Tile_Niv3/GroundIarbaApa/_086", false);
        setup(268, "/Tile_Niv3/GroundIarbaApa/_087", false);
        setup(269, "/Tile_Niv3/GroundIarbaApa/_088", true);
        setup(270, "/Tile_Niv3/GroundIarbaApa/_089", true);
        setup(271, "/Tile_Niv3/GroundIarbaApa/_097", false);
        setup(272, "/Tile_Niv3/GroundIarbaApa/_098", false);
        setup(273, "/Tile_Niv3/GroundIarbaApa/_100", true);
        setup(274, "/Tile_Niv3/GroundIarbaApa/_101", true);
        setup(275, "/Tile_Niv3/GroundIarbaApa/_108", false);
        setup(276, "/Tile_Niv3/GroundIarbaApa/_109", false);
        setup(277, "/Tile_Niv3/GroundIarbaApa/_119", false);
        setup(278, "/Tile_Niv3/GroundIarbaApa/_120", false);
        setup(279, "/Tile_Niv3/GroundIarbaApa/_130", false);
        setup(280, "/Tile_Niv3/GroundIarbaApa/_131", false);
        setup(281, "/Tile_Niv3/GroundIarbaApa/_132", false);
        setup(282, "/Tile_Niv3/GroundIarbaApa/_133", false);
        setup(283, "/Tile_Niv3/GroundIarbaApa/_134", false);
        setup(284, "/Tile_Niv3/GroundIarbaApa/_135", true);
        setup(285, "/Tile_Niv3/GroundIarbaApa/_136", true);
        setup(286, "/Tile_Niv3/GroundIarbaApa/_137", true);
        setup(307,"/Tile_Niv2/IarbaApa/Apa1",false);
        //Poarta
        setup(287, "/Tile_Niv3/Poarta/1", true);
        setup(288, "/Tile_Niv3/Poarta/2", true);

        //Magazin
        setup(289, "/Tile_Niv3/Ornamente/Taraba/1", false);
        setup(290, "/Tile_Niv3/Ornamente/Taraba/2", false);
        setup(291, "/Tile_Niv3/Ornamente/Taraba/3", false);
        setup(292, "/Tile_Niv3/Ornamente/Taraba/4", true);
        setup(293, "/Tile_Niv3/Ornamente/Taraba/5", true);
        setup(294, "/Tile_Niv3/Ornamente/Taraba/6", true);
        setup(295, "/Tile_Niv3/Ornamente/Taraba/7", true);
        setup(296, "/Tile_Niv3/Ornamente/Taraba/8", true);
        setup(297, "/Tile_Niv3/Ornamente/Taraba/9", true);

        setup(298, "/Tile_Niv3/Ornamente/TarabaMica/1", true);
        setup(299, "/Tile_Niv3/Ornamente/TarabaMica/2", true);
        setup(300, "/Tile_Niv3/Ornamente/TarabaMica/3", true);
        setup(301, "/Tile_Niv3/Ornamente/TarabaMica/4", true);
        setup(302, "/Tile_Niv3/Ornamente/TarabaMica/5", true);
        setup(303, "/Tile_Niv3/Ornamente/TarabaMica/6", true);
        setup(304, "/Tile_Niv3/Ornamente/TarabaMica/7", true);
        setup(305, "/Tile_Niv3/Flori/l0_Mario_Naruto298", true);
        setup(306, "/Tile_Niv3/Flori/l0_Mario_Naruto301", true);

    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath, int[][][] layer, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(",");

                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);
                    layer[map][col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawGroundAndObjects(Graphics2D g2) {
        if (gp.currentMap == 0) {
            drawLayer(g2, groundLayer1);
            drawLayer(g2, treesDeLayer1);
            drawLayer(g2, objectsLayer1);
        } else if (gp.currentMap == 1) {
            drawLayer(g2, groundLayer2);
            drawLayer(g2, IarbaApa);
            drawLayer(g2, PodPlanteCasaCopacColiziune);
        } else {
            drawLayer(g2, groundLayer3);
            drawLayer(g2, IarbaApa3);
            drawLayer(g2, Obiecte3);
        }
    }
    public void drawTrees(Graphics2D g2) {
        if(gp.currentMap == 0) {
            drawLayer(g2, treesDLayer1);
        }
        else if(gp.currentMap == 1) {
            drawLayer(g2,CopacCasaNecoliziune);
        } else {
            drawLayer(g2,ObiecteDedesubt3);
        }
    }
    private void drawLayer(Graphics2D g2, int[][][] layer) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            int tileNum = layer[gp.currentMap][worldCol][worldRow];

            //Stop moving the cameraat edge
            /*if(gp.player.screenX > gp.player.worldX) {
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }
            */
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                if (tileNum != -1) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }
            /*else if (( gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    bottomOffset > gp.worldHeight - gp.player.worldY ) && tileNum != -1 ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            */
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
