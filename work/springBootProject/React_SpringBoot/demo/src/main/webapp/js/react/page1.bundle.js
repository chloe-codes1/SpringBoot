/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 1);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */,
/* 1 */
/***/ (function(module, exports) {

throw new Error("Module build failed (from /home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/babel-loader/lib/index.js):\nBrowserslistError: [BABEL] /home/chloe/Workspace/work/React_SpringBoot/demo/src/main/jsx/Page1Page.jsx: Unknown browser query `var fs = require('fs')`. Maybe you are using old Browserslist or made typo in query. (While processing: \"/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/preset-env/lib/index.js\")\n    at unknownQuery (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/browserslist/index.js:201:10)\n    at /home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/browserslist/index.js:288:11\n    at Array.reduce (<anonymous>)\n    at resolve (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/browserslist/index.js:234:18)\n    at browserslist (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/browserslist/index.js:358:21)\n    at getTargets (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/preset-env/lib/targets-parser.js:179:48)\n    at _default (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/preset-env/lib/index.js:225:46)\n    at /home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/helper-plugin-utils/lib/index.js:19:12\n    at loadDescriptor (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/full.js:166:14)\n    at cachedFunction (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/caching.js:32:19)\n    at loadPresetDescriptor (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/full.js:256:36)\n    at config.presets.reduce (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/full.js:79:21)\n    at Array.reduce (<anonymous>)\n    at recurseDescriptors (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/full.js:76:38)\n    at loadFullConfig (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/config/full.js:109:6)\n    at process.nextTick (/home/chloe/Workspace/work/React_SpringBoot/demo/node_modules/@babel/core/lib/transform.js:28:33)\n    at process._tickCallback (internal/process/next_tick.js:61:11)");

/***/ })
/******/ ]);
//# sourceMappingURL=page1.bundle.js.map