/**
 * Created by heqiang on 6/24/17.
 */
const express = require('express');
const router = express.Router();
const db = require('../db/schema');
const fs = require('fs');
const path = require('path');

router.get('/', function (req, res) {
    res.redirect('/tools/encode');
});
router.get('/encode', function (req, res) {
    res.render('tools', {menu: 'encode'});
});
router.get('/json', function (req, res) {
    res.render('json', {menu: 'json'});
});
router.get('/pic', function (req, res) {
    res.render('pic', {menu: 'pic'});
});
router.get('/apk', function (req, res) {
    res.render('apk', {menu: 'apk'});
});
module.exports = router;