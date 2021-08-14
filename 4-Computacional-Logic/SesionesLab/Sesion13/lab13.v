(* Definición inductiva de naturales *)
Inductive natural: Type :=
|cero : natural
|succ : natural -> natural.

(* Suma en naturales *)
Fixpoint add (n1 n2:natural) : natural :=
  match n2 with
  | cero => n1
  | succ n2' => succ (add n1 n2')
  end.

(* Multiplicación en naturales *)
Fixpoint mul (n1 n2:natural) :natural :=
  match n2 with
  | cero => cero
  | succ n2' => add (mul n1 n2') n1
  end.


(* AQUÍ VAN SUS PRUEBAS *)

Lemma cero_neutro_izq: forall (n:natural), add cero n = n.
Proof.
induction n.
reflexivity.
rewrite <- IHn.
simpl.
rewrite IHn.
rewrite IHn.
trivial.
Qed.

Lemma suc_mas_1: forall (n:natural), add n (succ cero) = succ(n).
Proof.
induction n.
reflexivity.
rewrite <- IHn.
simpl.
trivial.
Qed.


Lemma suc_mas_aux: forall (n:natural), add (succ cero) n = succ(n).
Proof.
induction n.
simpl.
trivial.
simpl.
rewrite <- IHn.
simpl.
trivial.
Qed.

Lemma cero_absorb_izq: forall (n:natural), mul cero n = cero.
Proof.
induction n.
reflexivity.
rewrite <- IHn.
rewrite IHn.
simpl.
trivial.
Qed.

Lemma saca_succs: forall (n1 n2: natural), add(succ n1) n2 = succ(add n1 n2).
Proof.
intros.
induction n2.
simpl.
reflexivity.
simpl.
rewrite <- IHn2.
reflexivity.
Qed.

Lemma add_conm: forall (n1 n2:natural), add n1 n2 = add n2 n1.
Proof.
intros.
induction n1.
simpl.
apply cero_neutro_izq.
simpl.
rewrite <- IHn1.
apply saca_succs.
Qed.

Lemma add_assoc: forall (a b c:natural), add (add a b) c = add a (add b c).
intros.
induction a.
rewrite cero_neutro_izq.
rewrite cero_neutro_izq.
reflexivity.
rewrite saca_succs.
rewrite saca_succs.
rewrite IHa.
simpl.
rewrite saca_succs.
reflexivity.
Qed.