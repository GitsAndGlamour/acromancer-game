import angular from 'angular'
import uirouter from 'angular-ui-router';
import sanitize from 'angular-sanitize';
import ngResource from 'angular-resource'
import ngCookies from 'angular-cookies'

import './common/css/application.css';
import {intercept, stateChangeSuccess, stateChangeStart, routing} from './app.config'
import common from './component/common';
import profile from './component/profile';
import bsTracker from './component/directives/bsHttpRequestTracker';
import homeComponent from './component/home';

const commonModule = angular.module('smlGame.common', [ngCookies]);
common(commonModule);

const profileModule = angular.module('smlGame.profile', [uirouter, ngResource, ngCookies, 'smlGame.common']);
profile(profileModule);

const bsTrackerModule = angular.module('smlGame.bsTracker', []);
bsTracker(bsTrackerModule);

const homeModule = angular.module('smlGame.home', [uirouter]);
homeComponent(homeModule);

require('./component/main')(angular.module('smlGame.main', [uirouter]));

const ngModule = angular.module('smlGame',
    [uirouter, sanitize, 'smlGame.common', 'smlGame.profile', 'smlGame.bsTracker', 'smlGame.home', 'smlGame.main'])
  .config(routing)
  .config(intercept)
  .run(stateChangeStart)
  .run(stateChangeSuccess);
