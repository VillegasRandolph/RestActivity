<?php
class Controller_Api extends Controller_Rest{
	
	public function get_users(){
		$data['users'] = Model_User::find('all');
		return $this->response($data);
	}
} 