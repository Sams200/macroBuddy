PGDMP  &                     |            postgres    16.0    16.0 /    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    5    postgres    DATABASE     |   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Europe.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    4853                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    16602    Entries    TABLE     {  CREATE TABLE public."Entries" (
    id integer NOT NULL,
    "Date" date DEFAULT CURRENT_DATE NOT NULL,
    "Food_id" integer,
    "Quantity" real NOT NULL,
    "Recipes_id" smallint,
    "Meal" character varying(15) NOT NULL,
    CONSTRAINT "check_FK" CHECK (((("Food_id" IS NOT NULL) AND ("Recipes_id" IS NULL)) OR (("Food_id" IS NULL) AND ("Recipes_id" IS NOT NULL)))),
    CONSTRAINT check_meal CHECK (((("Meal")::text = 'Breakfast'::text) OR (("Meal")::text = 'Lunch'::text) OR (("Meal")::text = 'Dinner'::text) OR (("Meal")::text = 'Snacks'::text))),
    CONSTRAINT check_quantity CHECK (("Quantity" > (0)::double precision))
);
    DROP TABLE public."Entries";
       public         heap    postgres    false            �            1259    16605    Entries_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Entries_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."Entries_id_seq";
       public          postgres    false    223            �           0    0    Entries_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Entries_id_seq" OWNED BY public."Entries".id;
          public          postgres    false    224            �            1259    16398    Food    TABLE     �  CREATE TABLE public."Food" (
    "Name" character varying(64) NOT NULL,
    "Producer_Id" integer NOT NULL,
    "KCal" integer NOT NULL,
    "Protein" real NOT NULL,
    "Fat" real NOT NULL,
    "Carbs" real NOT NULL,
    "Serving_Size" real,
    "Serving_Units" character varying(20) NOT NULL,
    id integer NOT NULL,
    CONSTRAINT check_carbs CHECK (("Carbs" >= (0)::double precision)),
    CONSTRAINT check_fat CHECK (("Fat" >= (0)::double precision)),
    CONSTRAINT check_kcal CHECK (("KCal" >= 0)),
    CONSTRAINT check_protein CHECK (("Protein" >= (0)::double precision)),
    CONSTRAINT check_servings CHECK (("Serving_Size" > (0)::double precision))
);
    DROP TABLE public."Food";
       public         heap    postgres    false            �            1259    16457    Food_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Food_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."Food_id_seq";
       public          postgres    false    216            �           0    0    Food_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public."Food_id_seq" OWNED BY public."Food".id;
          public          postgres    false    221            �            1259    16469    Ingredients_List    TABLE     �   CREATE TABLE public."Ingredients_List" (
    "Food_id" integer NOT NULL,
    "Recipe_id" integer NOT NULL,
    "Quantity" real NOT NULL,
    CONSTRAINT check_quantity CHECK (("Quantity" > (0)::double precision))
);
 &   DROP TABLE public."Ingredients_List";
       public         heap    postgres    false            �            1259    16425 	   Producers    TABLE     h   CREATE TABLE public."Producers" (
    "Name" character varying(64) NOT NULL,
    id integer NOT NULL
);
    DROP TABLE public."Producers";
       public         heap    postgres    false            �            1259    16450    Producers_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Producers_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public."Producers_id_seq";
       public          postgres    false    217            �           0    0    Producers_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public."Producers_id_seq" OWNED BY public."Producers".id;
          public          postgres    false    220            �            1259    16436    Recipes    TABLE     h   CREATE TABLE public."Recipes" (
    id smallint NOT NULL,
    "Name" character varying(255) NOT NULL
);
    DROP TABLE public."Recipes";
       public         heap    postgres    false            �            1259    16435    Recipes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Recipes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."Recipes_id_seq";
       public          postgres    false    219            �           0    0    Recipes_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Recipes_id_seq" OWNED BY public."Recipes".id;
          public          postgres    false    218            �            1259    16874    recipes_calories    VIEW     �  CREATE VIEW public.recipes_calories AS
 SELECT r."Name",
    sum(((((f."KCal")::double precision * il."Quantity"))::numeric)::integer) AS "Kcal",
    sum((f."Protein" * il."Quantity")) AS "Protein",
    sum((f."Fat" * il."Quantity")) AS "Fat",
    sum((f."Carbs" * il."Quantity")) AS "Carbs",
    il."Recipe_id"
   FROM ((public."Food" f
     JOIN public."Ingredients_List" il ON ((f.id = il."Food_id")))
     JOIN public."Recipes" r ON ((r.id = il."Recipe_id")))
  GROUP BY r."Name", il."Recipe_id";
 #   DROP VIEW public.recipes_calories;
       public          postgres    false    216    216    216    216    216    219    219    222    222    222            �            1259    16821    water    TABLE     �   CREATE TABLE public.water (
    day date DEFAULT CURRENT_DATE NOT NULL,
    quantity smallint DEFAULT 0 NOT NULL,
    CONSTRAINT check_name CHECK ((quantity >= 0))
);
    DROP TABLE public.water;
       public         heap    postgres    false            �           0    0    COLUMN water.quantity    COMMENT     1   COMMENT ON COLUMN public.water.quantity IS 'ml';
          public          postgres    false    225            9           2604    16606 
   Entries id    DEFAULT     l   ALTER TABLE ONLY public."Entries" ALTER COLUMN id SET DEFAULT nextval('public."Entries_id_seq"'::regclass);
 ;   ALTER TABLE public."Entries" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            6           2604    16458    Food id    DEFAULT     f   ALTER TABLE ONLY public."Food" ALTER COLUMN id SET DEFAULT nextval('public."Food_id_seq"'::regclass);
 8   ALTER TABLE public."Food" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    216            7           2604    16451    Producers id    DEFAULT     p   ALTER TABLE ONLY public."Producers" ALTER COLUMN id SET DEFAULT nextval('public."Producers_id_seq"'::regclass);
 =   ALTER TABLE public."Producers" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    217            8           2604    16847 
   Recipes id    DEFAULT     l   ALTER TABLE ONLY public."Recipes" ALTER COLUMN id SET DEFAULT nextval('public."Recipes_id_seq"'::regclass);
 ;   ALTER TABLE public."Recipes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �          0    16602    Entries 
   TABLE DATA           \   COPY public."Entries" (id, "Date", "Food_id", "Quantity", "Recipes_id", "Meal") FROM stdin;
    public          postgres    false    223   	8       �          0    16398    Food 
   TABLE DATA              COPY public."Food" ("Name", "Producer_Id", "KCal", "Protein", "Fat", "Carbs", "Serving_Size", "Serving_Units", id) FROM stdin;
    public          postgres    false    216   9       �          0    16469    Ingredients_List 
   TABLE DATA           P   COPY public."Ingredients_List" ("Food_id", "Recipe_id", "Quantity") FROM stdin;
    public          postgres    false    222   w:       �          0    16425 	   Producers 
   TABLE DATA           1   COPY public."Producers" ("Name", id) FROM stdin;
    public          postgres    false    217   �:       �          0    16436    Recipes 
   TABLE DATA           /   COPY public."Recipes" (id, "Name") FROM stdin;
    public          postgres    false    219   ";       �          0    16821    water 
   TABLE DATA           .   COPY public.water (day, quantity) FROM stdin;
    public          postgres    false    225   n;       �           0    0    Entries_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Entries_id_seq"', 57, true);
          public          postgres    false    224            �           0    0    Food_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."Food_id_seq"', 25, true);
          public          postgres    false    221            �           0    0    Producers_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."Producers_id_seq"', 10, true);
          public          postgres    false    220                        0    0    Recipes_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Recipes_id_seq"', 10, true);
          public          postgres    false    218            N           2606    16611    Entries Entries_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public."Entries"
    ADD CONSTRAINT "Entries_pk" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Entries" DROP CONSTRAINT "Entries_pk";
       public            postgres    false    223            H           2606    16463    Food Food_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public."Food"
    ADD CONSTRAINT "Food_pk" PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."Food" DROP CONSTRAINT "Food_pk";
       public            postgres    false    216            J           2606    16456    Producers Producers_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public."Producers"
    ADD CONSTRAINT "Producers_pk" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Producers" DROP CONSTRAINT "Producers_pk";
       public            postgres    false    217            L           2606    16849    Recipes Recipes_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public."Recipes"
    ADD CONSTRAINT "Recipes_pk" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Recipes" DROP CONSTRAINT "Recipes_pk";
       public            postgres    false    219            P           2606    16827    water water_pk 
   CONSTRAINT     M   ALTER TABLE ONLY public.water
    ADD CONSTRAINT water_pk PRIMARY KEY (day);
 8   ALTER TABLE ONLY public.water DROP CONSTRAINT water_pk;
       public            postgres    false    225            T           2606    16615    Entries Entries_Food_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."Entries"
    ADD CONSTRAINT "Entries_Food_id_fk" FOREIGN KEY ("Food_id") REFERENCES public."Food"(id);
 H   ALTER TABLE ONLY public."Entries" DROP CONSTRAINT "Entries_Food_id_fk";
       public          postgres    false    223    4680    216            U           2606    16855    Entries Entries_Recipes_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."Entries"
    ADD CONSTRAINT "Entries_Recipes_id_fk" FOREIGN KEY ("Recipes_id") REFERENCES public."Recipes"(id);
 K   ALTER TABLE ONLY public."Entries" DROP CONSTRAINT "Entries_Recipes_id_fk";
       public          postgres    false    223    219    4684            Q           2606    16464    Food Food_Producers_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."Food"
    ADD CONSTRAINT "Food_Producers_id_fk" FOREIGN KEY ("Producer_Id") REFERENCES public."Producers"(id);
 G   ALTER TABLE ONLY public."Food" DROP CONSTRAINT "Food_Producers_id_fk";
       public          postgres    false    216    217    4682            R           2606    16850 7   Ingredients_List Ingredients_List_Ingredients_List__fk2    FK CONSTRAINT     �   ALTER TABLE ONLY public."Ingredients_List"
    ADD CONSTRAINT "Ingredients_List_Ingredients_List__fk2" FOREIGN KEY ("Recipe_id") REFERENCES public."Recipes"(id);
 e   ALTER TABLE ONLY public."Ingredients_List" DROP CONSTRAINT "Ingredients_List_Ingredients_List__fk2";
       public          postgres    false    4684    219    222            S           2606    16472 '   Ingredients_List Ingredients_List___fk1    FK CONSTRAINT     �   ALTER TABLE ONLY public."Ingredients_List"
    ADD CONSTRAINT "Ingredients_List___fk1" FOREIGN KEY ("Food_id") REFERENCES public."Food"(id);
 U   ALTER TABLE ONLY public."Ingredients_List" DROP CONSTRAINT "Ingredients_List___fk1";
       public          postgres    false    4680    222    216            �   �   x�e���0Dg�_@��X��U��,Q�Bb���7%�ؠly��}���
Ă, B{��s���ɂB���b��K�6u��2��0V���g�&V���k��_�v]�>뚽����4�x�^o�R9�8�<�d�85�]��n�����NAm�����J��o-)��]#Y^0��Uo�Q� 6��ԧ��eXEӠ�d��ߒ�*��Z�Dn����H�k���$.�/�����6�n      �   b  x�MQ�n�0������e?lc5S�F��"V�B!24_�{	i+O�����۷�;ۍ�SOL�VĬY]kK��#�Z��9�>[���f�dO9`�Ȑ5��վMs��S����dtN\.�pD� C��
��J����N<�>��-hP��zA�r ���>C>��	�����;��P��0��v8�I�e�_k��25m�6	6<`-@IZ��H�#"�7��$��C�|����z�]WSA\�SP�~�S���uv��Gݤ̈�@�����N�Tj�NQ��k*%R�o�P�b��s�	��"Ps���7�Wo|D�ؒ7$��/i&����T����%�&S�Z)���x�      �   0   x�3�4�4� �z\��F��\F`�H�IC eh���b���� �*�      �   [   x��K
� ��{wI�?����6Fh�	���ZW*���2V^E.s��`x�W]�B����Ǆ��Fs�q2k���kZ11h�3-z�<�3���[�      �   <   x�3�(�/I��SpN-JM��2�tJL��S��M�I-)I�24�����.�H�N����� ���      �   A   x�U�A
�0C�u�.S����ϡC� d��1�S~d�3����ݕ&��I%O��W��}�%     